package com.capstone.scheduledevelop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 100, message = "내용은 100자 이내여야 합니다.")
    private String content;
}
