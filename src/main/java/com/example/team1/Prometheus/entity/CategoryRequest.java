package com.example.team1.Prometheus.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    private Long categoryId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
