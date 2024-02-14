package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.CommentRepo;
import com.example.demo.Requests.CommentCreate;
import com.example.demo.Requests.CommentUpdate;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;

@Service
public class CommentService {

    private CommentRepo commentRepo;
    private UserService userService;
    private PostService postService;

    public CommentService(CommentRepo commentRepo, UserService userService, PostService postService) {
        this.commentRepo = commentRepo;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {

        if (userId != null && postId != null) {
            return commentRepo.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId != null) {
            return commentRepo.findByUserId(userId.get());
        } else if (postId != null) {
            return commentRepo.findByPostId(postId.get());
        } else {
            return commentRepo.findAll();
        }
    }

    public Comment getCommentById(Long commentId) {
        return commentRepo.findById(commentId).orElse(null);
    }

    public Comment updateCommentById(Long commentId, CommentUpdate request) {
        Optional<Comment> comment = commentRepo.findById(commentId);
        if (comment != null) {
            Comment updateComment = comment.get();
            updateComment.setText(request.getText());
            commentRepo.save(updateComment);
            return updateComment;
        } else {
            return null;
        }
    }

    public Comment createCommentById(CommentCreate request) {
        User user = userService.getPerson(request.getUserId());
        Post post = postService.getPostById(request.getPostId());

        if (user != null && post != null) {
            Comment comment = new Comment();
            comment.setId(request.getId());
            comment.setText(request.getText());
            comment.setUser(user);
            comment.setPost(post);
            return commentRepo.save(comment);
        }
        return null;
    }

    public void deleteCommentById(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}