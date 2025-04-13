package com.tpnam.spring_boot.controller;

import com.tpnam.spring_boot.dto.PostDTO;
import com.tpnam.spring_boot.entity.Post;
import com.tpnam.spring_boot.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@PathVariable Long userId, @RequestBody PostDTO dto) {
        return new ResponseEntity<>(postService.createPost(userId, dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(postService.getPostsByUser(userId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id)); // Nếu không tìm thấy, PostNotFoundException sẽ được ném ra
    }
}
