package com.capstone.scheduledevelop.dto;

import com.capstone.scheduledevelop.entity.Schedule;
import com.capstone.scheduledevelop.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String username;

    private final String email;

    public UserResponseDto(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(user.getUsername(), user.getEmail());
    }
}
