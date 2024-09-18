package com.elice.team1.prometheus.item.dto;

import com.elice.team1.prometheus.category.entity.Category;
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
    private Category category;
    private String imagePath;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
