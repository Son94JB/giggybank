package com.d208.giggyapp.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.d208.giggyapp.domain.Board.LikePost;
import com.d208.giggyapp.domain.Board.Post;
import com.d208.giggyapp.domain.User;
import com.d208.giggyapp.dto.Board.PostCreateDto;
import com.d208.giggyapp.dto.Board.PostDto;
import com.d208.giggyapp.dto.Board.PostListDto;
import com.d208.giggyapp.dto.Board.PostUpdateDto;
import com.d208.giggyapp.repository.Board.CommentRepository;
import com.d208.giggyapp.repository.Board.LikePostRepository;
import com.d208.giggyapp.repository.Board.PostRepository;
import com.d208.giggyapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final S3Service s3Service;

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long createPost(MultipartFile file, PostCreateDto postCreateDto) {
        User user = userRepository.findById(postCreateDto.getUserId()).orElse(null);

        if(user != null){
            // 파일 저장
            String imageUrl = s3Service.uploadFile(file).getBody();

            // 회원 저장
            Post post = Post.toEntity(postCreateDto, user);
            post.setImageUrl(imageUrl);
            postRepository.save(post);

            return post.getId();
        }
        else {
            throw new IllegalArgumentException("유저 없음");
        }
    }

    @Transactional
    public void deletePost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물 없음"));

        postRepository.delete(post);
    }


    @Transactional
    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물 없음"));

        // 좋아요수
        int likeCnt = likePostRepository.countByPostId(postId);
        // 좋아요 유무
        int liked = likePostRepository.countByPostIdAndUserId(postId, post.getUser().getId());



        Boolean isLiked = false;
        if(liked == 1) {
            isLiked = true;
        }

        // 조회수
        postRepository.increaseViewCnt(postId);
        post.increaseViewCnt();

        return new PostDto(post, likeCnt, isLiked);

    }


    @Transactional
    public void updatePost(PostUpdateDto postUpdateDto, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물 없음"));
        post.update(postUpdateDto);
    }


    @Transactional
    public List<PostListDto> getPostList(String keyword) {
        List<Post> posts = postRepository.findAllByTitleContainingIgnoreCaseOrderByIdDesc(keyword);
        List<PostListDto> postListDtos = new ArrayList<>();

        for(Post post : posts) {
            int likeCnt = likePostRepository.countByPostId(post.getId());
            int commentCnt = commentRepository.countByPostId(post.getId());
            postListDtos.add(new PostListDto(post, likeCnt, commentCnt));
        }
        return postListDtos;
    }

    @Transactional
    public void togglePostLike(Long postId, UUID userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물 없음"));
        User user = userRepository.findById(userId).orElse(null);
    if (likePostRepository.countByPostIdAndUserId(postId, userId)== 0) {
            likePostRepository.save(LikePost.builder()
                    .post(post)
                    .user(user)
                    .build());
        } else {
            likePostRepository.deleteByPostIdAndUserId(postId, userId);
        }
    }
}