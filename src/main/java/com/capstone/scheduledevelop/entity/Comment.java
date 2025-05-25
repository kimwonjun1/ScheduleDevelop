package com.capstone.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "longtext")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

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
