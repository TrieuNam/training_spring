package com.tpnam.spring_boot.service;

import com.tpnam.spring_boot.dto.UserDTO;
import com.tpnam.spring_boot.exception.UserNotFoundException;
import com.tpnam.spring_boot.mapper.UserMapper;
import com.tpnam.spring_boot.model.User;
import com.tpnam.spring_boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public UserDTO getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

        return userMapper.toDto(user);
    }

    public UserDTO  create(UserDTO  userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDTO  update(Long id, UserDTO  userDTO) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
        existing.setName(userDTO.getName());
        existing.setEmail(userDTO.getEmail());
        existing.setAge(userDTO.getAge());
        return userMapper.toDto(existing);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id " + id);
        }

        userRepository.deleteById(id);
    }
}
