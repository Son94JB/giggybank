package com.d208.giggyapp.dto.board;

import com.d208.giggyapp.domain.board.Category;
import com.d208.giggyapp.domain.board.Post;
import com.d208.giggyapp.domain.board.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostListDto {
    private Long id;
    private UUID userId;
    private String nickName;
    private String title;
    private String content;
    private PostType postType;
    private Category category;
    private int viewCount;
    private int likeCnt;
    private int commentCnt;

    public PostListDto(Post post, int likeCnt, int commentCnt) {
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.nickName = post.getUser().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postType = post.getPostType();
        this.category = post.getCategory();
        this.viewCount = post.getViewCount();
        this.likeCnt = likeCnt;
        this.commentCnt = commentCnt;

    }

}
