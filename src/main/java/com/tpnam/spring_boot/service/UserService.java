package com.tpnam.spring_boot.service;

import com.tpnam.spring_boot.dto.UserDTO;
import com.tpnam.spring_boot.exception.UserNotFoundException;
import com.tpnam.spring_boot.entity.User;
import com.tpnam.spring_boot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


//    private final UserMapper userMapper;

    private final ModelMapper modelMapper;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    // DTO manual
//    public User create(User user) {
//        return userRepository.save(user);
//    }

//    public UserDTO create(UserDTO  userDTO) {
//        User user = userMapper.toEntity(userDTO);
//        User saved = userRepository.save(user);
//        return userMapper.toDto(saved);
//    }

    public User update(Long id, User user) {
        User existing = getById(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setAge(user.getAge());
        return userRepository.save(existing);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public UserDTO toDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User toEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    public List<UserDTO> toDtoList(List<User> users) {
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();
    }
}
