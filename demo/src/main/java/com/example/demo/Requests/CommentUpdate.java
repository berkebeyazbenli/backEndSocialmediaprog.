package com.example.demo.Requests;

import lombok.Data;

@Data
public class CommentUpdate {
    Long id;
    Long userId;
    Long postId;
    String text;

}
