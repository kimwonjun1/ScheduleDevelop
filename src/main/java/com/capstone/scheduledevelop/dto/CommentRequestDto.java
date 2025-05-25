package com.capstone.scheduledevelop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "내용은 필수입니다.")
    @Size(max = 100, message = "내용은 100자 이내여야 합니다.")
    private final String content;

    @NotNull(message = "유저 ID는 필수입니다.")
    private final Long userId;

    @NotNull(message = "일정 ID은 필수입니다.")
    private final Long scheduleId;

    public CommentRequestDto(String content, Long userId, Long scheduleId) {
        this.content = content;
        this.userId = userId;
        this.scheduleId = scheduleId;
    }
}
