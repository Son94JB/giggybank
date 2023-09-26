package com.d208.giggyapp.dto.Board;


import com.d208.giggyapp.domain.Board.Category;
import com.d208.giggyapp.domain.Board.Post;
import com.d208.giggyapp.domain.Board.PostType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDto {
//    private UUID userId;

    private String title;

    private String content;

    private PostType postType;

    private Category category;

    public PostUpdateDto(Post post) {
        this.title= post.getTitle();
        this.content = post.getContent();
        this.postType = post.getPostType();
        this.category = post.getCategory();
    }

//    public Post toEntity() {
//        return Post.builder()
//                .title(title)
//                .content(content)
//                .postType(postType)
//                .category(category)
//                .build();
//    }

}
