package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Post;

public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long userId);
}
