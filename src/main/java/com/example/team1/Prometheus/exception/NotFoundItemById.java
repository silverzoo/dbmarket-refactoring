package com.example.team1.Prometheus.exception;

public class NotFoundItemById extends IllegalArgumentException{
    public NotFoundItemById(Long id) {
        super("\'게시글 ID " + id + "\' 은 존재하지 않습니다.");
    }
}