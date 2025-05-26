package com.capstone.scheduledevelop.dto.schedule;


import com.capstone.scheduledevelop.entity.Schedule;
import lombok.Getter;


@Getter
public class SchedulePageResponseDto {

    private final Long id;

    private final String title;

    private final String content;

    private final int commentCount;

    private final String username;

    public SchedulePageResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.commentCount = schedule.getComments() != null ? schedule.getComments().size() : 0; // null인 경우 0
        this.username = schedule.getUser().getUsername();
    }
}
