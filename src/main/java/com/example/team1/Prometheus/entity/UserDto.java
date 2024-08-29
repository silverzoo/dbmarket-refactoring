package com.example.team1.Prometheus.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private Double rate;

    public User toEntity() {
        return new User(null,username, password, rate);
    }
}
