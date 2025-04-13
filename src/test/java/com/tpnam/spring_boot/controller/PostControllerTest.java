package com.tpnam.spring_boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpnam.spring_boot.dto.PostDTO;
import com.tpnam.spring_boot.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @MockBean  Spring Boot 3.4.0, annotation @MockBean đã được đánh dấu là @Deprecated
//    private PostService postService;

    @Autowired
    private PostService postService; // mock do ta cung cấp trong config

    @TestConfiguration
    static class MockConfig {
        @Bean
        public PostService postService() {
            return Mockito.mock(PostService.class);
        }
    }

    @Test
    void testCreatePost() throws Exception {
        PostDTO post = new PostDTO(null, "Spring Boot", "Hướng dẫn Spring Boot");
        PostDTO saved = new PostDTO(1L, "Spring Boot", "Hướng dẫn Spring Boot");

        Mockito.when(postService.createPost(Mockito.eq(1L), Mockito.any())).thenReturn(saved);

        mockMvc.perform(post("/users/1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(post)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Spring Boot"));
    }
}