package com.d208.giggyapp.repository.board;

import com.d208.giggyapp.domain.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying
    @Query("update Post p set p.viewCount = p.viewCount + 1 where p.id = :id")
    void increaseViewCnt(@Param("id") Long id);

    List<Post> findAllByTitleContainingIgnoreCaseOrderByIdDesc(String keyword);
}
