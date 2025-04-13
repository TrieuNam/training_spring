package com.tpnam.spring_boot.service;

import com.tpnam.spring_boot.dto.UserDTO;
import com.tpnam.spring_boot.mapper.UserMapper;
import com.tpnam.spring_boot.entity.User;
import com.tpnam.spring_boot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;


    @Test
    public void testCreateUser() {

        // Arrange
        UserDTO inputDto = new UserDTO();
        inputDto.setName("Nam Trieu");
        inputDto.setEmail("nam@example.com");

        User mappedUser = new User();
        mappedUser.setName("Nam Trieu");
        mappedUser.setEmail("nam@example.com");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Nam Trieu");
        savedUser.setEmail("nam@example.com");

        UserDTO savedDto = new UserDTO();
        savedDto.setName("Nam Trieu");
        savedDto.setEmail("nam@example.com");

        when(userMapper.toEntity(inputDto)).thenReturn(mappedUser);
        when(userRepository.save(mappedUser)).thenReturn(savedUser);
        when(userMapper.toDto(savedUser)).thenReturn(savedDto);

        // Act
        UserDTO result = userService.create(inputDto);

        // Assert
        assertNotNull(result);
        assertEquals("Nam Trieu", result.getName());
        assertEquals("nam@example.com", result.getEmail());

        verify(userMapper).toEntity(inputDto);
        verify(userRepository).save(mappedUser);
        verify(userMapper).toDto(savedUser);

    }
}