package com.example.team1.Prometheus.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ItemModifyResponse {
    private Long itemId;
    private Long userId;
    private String name;
    private int price;
    private String category;
    private String imagePath;
    private String description;
}
