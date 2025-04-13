package com.tpnam.spring_boot.service;

import com.tpnam.spring_boot.dto.UserDTO;
import com.tpnam.spring_boot.entity.User;
import com.tpnam.spring_boot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testCreateUser() {

        User user = new User(1L, "Nam Trieu", "nam@example.com","28");
        UserDTO dto = new UserDTO("Nam Trieu", "nam@example.com","28");

        Mockito.when(modelMapper.map(user, UserDTO.class)).thenReturn(dto);

        UserDTO result = userService.toDto(user);

        assertEquals("Nam Trieu", result.getName());
        assertEquals("nam@example.com", result.getEmail());

    }
}