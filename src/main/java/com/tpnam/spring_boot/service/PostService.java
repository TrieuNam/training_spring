package com.tpnam.spring_boot.service;

import com.tpnam.spring_boot.dto.PostDTO;
import com.tpnam.spring_boot.entity.Post;
import com.tpnam.spring_boot.entity.User;
import com.tpnam.spring_boot.exception.PostNotFoundException;
import com.tpnam.spring_boot.exception.UserNotFoundException;
import com.tpnam.spring_boot.mapper.PostMapper;
import com.tpnam.spring_boot.repository.PostRepository;
import com.tpnam.spring_boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;


    public PostDTO createPost(Long userId, PostDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->new UserNotFoundException("User not found with id " + userId));

        Post post = postMapper.toEntity(dto);
        post.setUser(user);

        return postMapper.toDto(postRepository.save(post));
    }

    public List<PostDTO> getPostsByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
           new UserNotFoundException("User not found with id " + userId);
        }

        return postRepository.findByUserId(userId).stream()
                .map(postMapper::toDto)
                .collect(Collectors.toList());
    }


    public PostDTO getPostById(Long postId) {
       Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId)); // Ném PostNotFoundException nếu không tìm thấy post

        return  postMapper.toDto(post);
    }

}
