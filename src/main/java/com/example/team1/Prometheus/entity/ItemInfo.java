package com.example.team1.Prometheus.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemInfo {
    private Long userId;
    private String name;
    private int price;
    private Long categoryId;
    private String category;
    private String imagePath;
    private String description;
}
