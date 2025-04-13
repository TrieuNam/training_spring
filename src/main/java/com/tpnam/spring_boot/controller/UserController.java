package com.tpnam.spring_boot.controller;

import com.tpnam.spring_boot.dto.UserDTO;
import com.tpnam.spring_boot.entity.User;
import com.tpnam.spring_boot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;


//    @GetMapping
//    public ResponseEntity<List<User>> getALl() {
//        return ResponseEntity.ok(userService.getAll());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }


//    @PostMapping
//    public ResponseEntity<User> create(@RequestBody @Valid UserDTO dto) {
//        User user = new User();
//        user.setName(dto.getName());
//        user.setEmail(dto.getEmail());
//        user.setAge(dto.getAge());
//
//        return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
//    }


//    @PostMapping
//    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO dto) {
//        return new ResponseEntity<>(userService.create(dto), HttpStatus.CREATED);
//    }

        @PostMapping
   public ResponseEntity<UserDTO> create(@RequestBody @Valid UserDTO dto) {
            User user = userService.toEntity(dto);
            return ResponseEntity.ok(userService.toDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody @Valid User dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
