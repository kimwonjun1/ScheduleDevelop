package com.capstone.scheduledevelop.controller;

import com.capstone.scheduledevelop.dto.*;
import com.capstone.scheduledevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {

        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                        signUpRequestDto.getUsername(),
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

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid UpdateUserRequestDto updateUserRequestDto) {

        userService.updatePassword(id,updateUserRequestDto.getOldPassword(), updateUserRequestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletRequest request) {

        LoginResponseDto loginResponseDto = userService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword(),
                request
        );

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

}
