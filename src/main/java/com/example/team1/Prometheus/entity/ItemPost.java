package com.example.team1.Prometheus.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import java.awt.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemPost{
    private String name;
    private int price;
    private String Category;
    private Image image;
    private String description;

    public String toString() {
        return "Form{" +
                "title='" + name + '\'' +
                ", content='" + price + '\'' +
                "title='" + Category + '\'' +
                ", content='" + image + '\'' +
                "title='" + description + '\'' +
                '}';
    }
}
