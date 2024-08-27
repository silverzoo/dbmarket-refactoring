package com.example.team1.Prometheus.exception;

public class UnauthorizedDeleteByUser extends SecurityException{
    public UnauthorizedDeleteByUser(Long userId) {
        super("\'" + userId + "\' 님은 삭제 권한이 없습니다.");
    }
}
