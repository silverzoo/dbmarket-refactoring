package com.elice.team1.dbmarket.item.dto;

import com.elice.team1.dbmarket.category.entity.Category;
import com.elice.team1.dbmarket.user.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemModifyRequest {
    private String name;
    private int price;
    // Fixme: 엔티티에서는 객체타입인 필드가 DTO에서는 Long? 객체타입?
    private Long category;
    private String imagePath;
    private String description;
}
