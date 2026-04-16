package com.mar1a_ed.e_store.controller;

import com.mar1a_ed.e_store.dto.user.UserCreateDto;
import com.mar1a_ed.e_store.dto.user.UserMapper;
import com.mar1a_ed.e_store.dto.user.UserResponseDto;
import com.mar1a_ed.e_store.dto.user.UserUpdatePasswordDto;
import com.mar1a_ed.e_store.model.entity.User;
import com.mar1a_ed.e_store.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto userCreateDto){
        User user = UserMapper.toUser(userCreateDto);
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @GetMapping("/id={id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        User user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(UserMapper.toDto(user));
    }

    @GetMapping("/email={email}")
    public ResponseEntity<UserResponseDto> findByEmail(@PathVariable String email){
        User user = userService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.FOUND).body(UserMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll(){
        List<User> users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toListDto(users));
    }

    @PatchMapping("/update-password/id={id}")
    public ResponseEntity<String> updatePassword(@PathVariable Long id, @Valid @RequestBody UserUpdatePasswordDto userUpdatePasswordDto){
        userService.updatePassword(id, userUpdatePasswordDto.getCurrentPassword(), userUpdatePasswordDto.getNewPassword(), userUpdatePasswordDto.getConfirmPassword());
        return ResponseEntity.ok().build();
    }
}
