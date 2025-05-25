package com.capstone.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Schedule 저장/삭제 시 Comment도 자동 저장/삭제, 리스트에서 삭제된 Comment는 DB에서도 삭제
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Schedule() {}

    public Schedule(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
