package com.capstone.scheduledevelop.dto;

import com.capstone.scheduledevelop.entity.Comment;
import com.capstone.scheduledevelop.entity.Schedule;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private final Long id;

    private final String content;

    private final Long userId;

    private final Long scheduleId;

    public CommentResponseDto(Long id, String content, Long userId, Long scheduleId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.scheduleId = scheduleId;
    }

    public static CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getId(),
                comment.getSchedule().getId()
        );
    }
}
