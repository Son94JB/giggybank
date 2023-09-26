package com.d208.giggyapp.repository.Board;

import com.d208.giggyapp.domain.Board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    int countByPostId(Long id);
}
