package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Repository.PostRepo;
import com.example.demo.Requests.PostCreateRequest;
import com.example.demo.Requests.PostUpdate;
import com.example.demo.entities.Post;

@Service
public class PostService {
    private PostRepo postRepo;
    private UserService userService;

    public PostService(PostRepo postRepo, UserService userService) {
        this.postRepo = postRepo;
        this.userService = userService;
    }

    public List<Post> getAllPost(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepo.findByUserId(userId.get());
        }
        return postRepo.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepo.findById(postId).orElse(null);
    }

    public Post savePost(Post newPost) {
        return postRepo.save(newPost);
    }

    public Post createPost(PostCreateRequest newPostRequest) {
        com.example.demo.entities.User user = userService.getPerson(newPostRequest.getUserId());
        if (user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepo.save(toSave);
    }

    public Post updatePostbyId(Long postId, PostUpdate newPostRequest) {
        Optional<Post> post = postRepo.findById(postId);
        if (post != null) {
            Post foundPost = post.get();
            foundPost.setText(newPostRequest.getText());
            foundPost.setTitle(newPostRequest.getTitle());
            postRepo.save(foundPost);
            return foundPost;
        } else
            return null;

    }

    public void deletePostbyId(Long postId) {
        postRepo.deleteById(postId);
    }

}
