package com.capstone.scheduledevelop.dto.schedule;

import lombok.Getter;

@Getter
public class ScheduleWithUserNameResponseDto {

    private final String title;

    private final String content;

    private final String username;

    public ScheduleWithUserNameResponseDto(String title, String content, String username) {
        this.title = title;
        this.content = content;
        this.username = username;
    }
}
