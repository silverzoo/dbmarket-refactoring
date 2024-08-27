package com.example.team1.Prometheus.entity;



import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Setter
@Getter
public class ItemPostDto{

    private ItemInfo itemInfo;
    private MultipartFile itemImage;


    public Item toEntity(){
        return new Item(null,itemInfo.getUserId(),itemInfo.getName(),itemInfo.getPrice(),itemInfo.getCategory(),itemInfo.getImagePath(),itemInfo.getDescription(),null,null);
    }
}