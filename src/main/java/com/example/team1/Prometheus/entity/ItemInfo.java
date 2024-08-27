package com.example.team1.Prometheus.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemInfo {
    private Long userId;
    private String name;
    private int price;
    private String category;
    private String imagePath;
    private String description;
}
