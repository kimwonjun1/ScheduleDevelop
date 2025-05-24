package com.capstone.scheduledevelop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequestDto {
    @NotBlank(message = "기존 비밀번호는 필수입니다.")
    private final String oldPassword;

    @NotBlank(message = "새 비밀번호는 필수입니다.")
    @Size(min = 5, message = "새 비밀번호는 최소 5자 이상이어야 합니다.")
    private final String newPassword;

    public UpdateUserRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
