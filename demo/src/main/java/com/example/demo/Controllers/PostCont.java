package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entities.Post;
import com.example.demo.Requests.PostCreateRequest;
import com.example.demo.Requests.PostUpdate;
import com.example.demo.Services.PostService;

@RestController
@RequestMapping("/posts")
public class PostCont {
    private PostService postService;

    public PostCont(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPost(userId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPostRequest) {
        return postService.createPost(newPostRequest);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdate newPostRequest) {
        return postService.updatePostbyId(postId, newPostRequest);
    }

    @DeleteMapping("/{postId}")
    public void DeletePost(@PathVariable Long postId) {
        postService.deletePostbyId(postId);
    }

}
