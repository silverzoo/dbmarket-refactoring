package com.example.team1.Prometheus.entity;



import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Setter
@Getter
public class ItemPostDto{
    private String name;
    private int price;
    private String category;
    private String imagePath;
    private String description;

    public ItemPost toEntity(){
        return new ItemPost(null,name,price,category,imagePath,description,LocalDateTime.now());
    }
}