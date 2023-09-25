package com.d208.giggyapp.domain.Board;

import com.d208.giggyapp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private String title;

    private String content;

    private String picture;

    private String postType;

    private String category;

    private int viewCount;

}
