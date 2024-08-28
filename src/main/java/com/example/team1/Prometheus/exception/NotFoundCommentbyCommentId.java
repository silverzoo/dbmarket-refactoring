package com.example.team1.Prometheus.exception;

public class NotFoundCommentbyCommentId extends IllegalArgumentException {
    public NotFoundCommentbyCommentId(Long commentId) {
        super("\'댓글 ID " + commentId + "\' 이 존재하지 않습니다.");
    }
}
