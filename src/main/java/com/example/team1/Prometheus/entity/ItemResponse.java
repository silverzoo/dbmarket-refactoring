package com.example.team1.Prometheus.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ItemResponse {
    private Long itemId;
    private Long userId;
    private String name;
    private int price;
    private Long categoryId;
    private String category;
    private String imagePath;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
