package com.capstone.scheduledevelop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "유저명은 필수입니다.")
    @Size(max = 4, message = "유저명은 4자 이내여야 합니다.")
    private final String username;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 5, message = "비밀번호는 최소 5자 이상이어야 합니다.")
    private final String password;

    public SignUpRequestDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
