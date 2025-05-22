package com.capstone.scheduledevelop.controller;

import com.capstone.scheduledevelop.dto.SignUpRequestDto;
import com.capstone.scheduledevelop.dto.SignUpResponseDto;
import com.capstone.scheduledevelop.dto.UserResponseDto;
import com.capstone.scheduledevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {

        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                        signUpRequestDto.getName(),
                        signUpRequestDto.getEmail(),
                        signUpRequestDto.getPassword()
                );

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

}
