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

import com.example.demo.Requests.CommentCreate;
import com.example.demo.Requests.CommentUpdate;
import com.example.demo.Services.CommentService;
import com.example.demo.entities.Comment;

@RestController
@RequestMapping("/comments")
public class CommentCont {
    private final CommentService commentService;

    public CommentCont(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PostMapping("/{commentId}")
    public Comment createComment(@RequestBody CommentCreate request) {
        return commentService.createCommentById(request);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdate request) {
        return commentService.updateCommentById(commentId, request);
    }

    @DeleteMapping("/commentId")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteCommentById(commentId);

    }
}