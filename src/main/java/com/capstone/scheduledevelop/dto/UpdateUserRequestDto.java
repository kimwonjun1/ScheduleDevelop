package com.capstone.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {
    private final String oldPassword;

    private final String newPassword;

    public UpdateUserRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
