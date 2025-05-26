package com.capstone.scheduledevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유저 고유 식별자

    @Column(nullable = false)
    private String username; // 유저명

    @Column(nullable = false)
    private String email; // 이메일

    @Column(nullable = false)
    private String password; // 비밀번호

    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public  void updatePassword(String password) {
        this.password = password;
    }

}
