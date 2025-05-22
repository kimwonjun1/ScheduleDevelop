package com.capstone.scheduledevelop.controller;

import com.capstone.scheduledevelop.dto.SignUpRequestDto;
import com.capstone.scheduledevelop.dto.SignUpResponseDto;
import com.capstone.scheduledevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
