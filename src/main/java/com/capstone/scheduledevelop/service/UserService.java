package com.capstone.scheduledevelop.service;

import com.capstone.scheduledevelop.dto.SignUpResponseDto;
import com.capstone.scheduledevelop.entity.User;
import com.capstone.scheduledevelop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public SignUpResponseDto signUp(String name, String email, String password) {

        User user = new User(name, email, password);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getEmail(), savedUser.getPassword());
    }
}
