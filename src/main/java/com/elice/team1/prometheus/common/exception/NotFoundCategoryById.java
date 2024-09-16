package com.elice.team1.prometheus.common.exception;

public class NotFoundCategoryById extends IllegalArgumentException{
    public NotFoundCategoryById(Long id) {
        super("\'카테고리 ID " + id + "\' 은 존재하지 않습니다.");
    }
}