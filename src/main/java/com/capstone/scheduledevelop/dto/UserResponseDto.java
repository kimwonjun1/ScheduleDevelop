package com.capstone.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String name;

    private final String email;

    public UserResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
