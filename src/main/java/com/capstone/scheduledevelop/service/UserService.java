package com.capstone.scheduledevelop.service;

import com.capstone.scheduledevelop.dto.SignUpResponseDto;
import com.capstone.scheduledevelop.dto.UserResponseDto;
import com.capstone.scheduledevelop.entity.User;
import com.capstone.scheduledevelop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public SignUpResponseDto signUp(String name, String email, String password) {

        User user = new User(name, email, password);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getEmail(), savedUser.getPassword());
    }

    public UserResponseDto findById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id : " + id); // 404
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getName(), findUser.getEmail());
    }
}
