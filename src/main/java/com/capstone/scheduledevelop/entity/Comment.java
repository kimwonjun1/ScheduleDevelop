package com.capstone.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 고유 식별자

    @Column(nullable = false, columnDefinition = "longtext")
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 작성자

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule; // 일정

    public Comment() {}

    public Comment(String content, User user, Schedule schedule) {

        this.content = content;
        this.user = user;
        this.schedule = schedule;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
