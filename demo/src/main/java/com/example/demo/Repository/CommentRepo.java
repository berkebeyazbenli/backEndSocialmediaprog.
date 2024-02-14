package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long postId);

    List<Comment> findByUserIdAndPostId(Long userId, Long postId);
}
