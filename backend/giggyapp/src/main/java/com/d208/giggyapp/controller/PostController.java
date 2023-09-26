package com.d208.giggyapp.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.d208.giggyapp.domain.User;
import com.d208.giggyapp.dto.Board.PostCreateDto;
import com.d208.giggyapp.dto.Board.PostDto;
import com.d208.giggyapp.dto.Board.PostListDto;
import com.d208.giggyapp.dto.Board.PostUpdateDto;
import com.d208.giggyapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/app")
public class PostController {

        private final PostService postService;

        // 게시글
        @PostMapping("/post")
        public ResponseEntity<?> createPost(@RequestPart(value = "file", required = false) MultipartFile file, @RequestPart PostCreateDto postCreateDto) {
                Long result = postService.createPost(file, postCreateDto);
                return ResponseEntity.status(201).body(result);
        }


        @GetMapping("/post")
        public List<PostListDto> getPosts(@RequestParam(value = "keyword", defaultValue = "") String keyword) {
                return postService.getPostList(keyword);
        }

        @GetMapping("/post/{postId}")
        public ResponseEntity<?> getPost(@PathVariable Long postId) {
                PostDto postDto = postService.getPost(postId);
                return ResponseEntity.ok(postDto);
        }

        @PutMapping("/post/{postId}")
        public ResponseEntity<Long> updatePost(@PathVariable Long postId, @RequestBody PostUpdateDto postUpdateDto) {
                postService.updatePost(postUpdateDto, postId);
                return ResponseEntity.ok().build();
        }

        @DeleteMapping("/post/{postId}")
        public void deletePost(@PathVariable Long postId) {
                postService.deletePost(postId);
        }

        @PostMapping("/post/{postId}/like")
        public void likePost(@PathVariable Long postId, @RequestBody UUID userId) {
                postService.togglePostLike(postId, userId);}

        // 댓글






}
