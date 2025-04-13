package com.tpnam.spring_boot.service;

import com.tpnam.spring_boot.dto.UserDTO;
import com.tpnam.spring_boot.entity.User;
import com.tpnam.spring_boot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "spring.profiles.active=test") //sử dụng databasse
public class UserServiceITTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setName("Nam Trieu");
        user.setEmail("nam@example.com");

        userRepository.save(user);
        UserDTO result = userService.toDto(user);

        assertEquals("Nam Trieu", result.getName());
        assertEquals("nam@example.com", result.getEmail());
    }
}
