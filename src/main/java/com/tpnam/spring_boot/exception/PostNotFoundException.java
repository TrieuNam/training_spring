package com.tpnam.spring_boot.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long postId) {
        super("Post not found with ID: " + postId);
    }
}
