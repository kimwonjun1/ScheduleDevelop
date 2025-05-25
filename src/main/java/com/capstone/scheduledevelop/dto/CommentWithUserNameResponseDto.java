package com.capstone.scheduledevelop.dto;

import lombok.Getter;

@Getter
public class CommentWithUserNameResponseDto {
    private final Long id;

    private final String content;

    private final String username;

    public CommentWithUserNameResponseDto(Long id, String content, String username) {
        this.id = id;
        this.content = content;
        this.username = username;
    }
}
