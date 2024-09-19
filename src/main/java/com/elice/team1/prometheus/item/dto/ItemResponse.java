package com.elice.team1.prometheus.item.dto;

import com.elice.team1.prometheus.category.entity.Category;
import com.elice.team1.prometheus.user.entity.User;
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
    private User user;
    private String name;
    private int price;
    private Category category;
    private String imagePath;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
