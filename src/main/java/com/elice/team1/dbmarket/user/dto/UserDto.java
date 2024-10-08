package com.elice.team1.dbmarket.user.dto;


import com.elice.team1.dbmarket.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private Double rate;

    public User toEntity() {
        return new User(username, password, rate);
    }
}
