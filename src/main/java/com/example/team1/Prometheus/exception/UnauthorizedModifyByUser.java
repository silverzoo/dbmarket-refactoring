package com.example.team1.Prometheus.exception;

public class UnauthorizedModifyByUser extends SecurityException{
    public UnauthorizedModifyByUser(String userName) {
        super("\'" + userName + "\' 님은 수정 권한이 없습니다.");
    }
}
