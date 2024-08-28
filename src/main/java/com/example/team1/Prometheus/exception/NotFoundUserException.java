package com.example.team1.Prometheus.exception;

public class NotFoundUserException extends IllegalArgumentException {
    public NotFoundUserException() {super("존재하지 않는 회원입니다.");}
}
