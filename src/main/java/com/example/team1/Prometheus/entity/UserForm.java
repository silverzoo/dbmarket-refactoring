package com.example.team1.Prometheus.entity;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserForm {
    private String username;
    private String password;

    public User toEntity() {
        return new User(null,username, password);
    }
}
