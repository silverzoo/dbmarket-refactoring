package com.elice.team1.dbmarket.item.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class ItemModifyResponse {
    private Long id;
    private Long userId;
    private Long categoryId;
    private String name;
    private int price;
    private String imagePath;
    private String description;
}
