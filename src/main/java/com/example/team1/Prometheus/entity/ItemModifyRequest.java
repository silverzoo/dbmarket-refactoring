package com.example.team1.Prometheus.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemModifyRequest {
    private Long userId;
    private String name;
    private int price;
    private String category;
    private String imagePath;
    private String description;
}
