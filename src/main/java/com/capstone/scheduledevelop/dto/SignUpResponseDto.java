package com.capstone.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private final Long id;

    private final String name;

    private final String email;

    public SignUpResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
