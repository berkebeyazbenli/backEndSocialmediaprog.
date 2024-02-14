package com.example.demo.Requests;

import lombok.Data;

@Data
public class CommentCreate {

    Long id;
    Long userId;
    Long postId;
    String text;

}
