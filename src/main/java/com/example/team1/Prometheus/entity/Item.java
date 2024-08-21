package com.example.team1.Prometheus.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Table(name="item")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", updatable = false)
    private Long itemId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "description")
    private String description;

    //엔티티 생성될 때 발행시간 저장하는 컬럼 추가
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    //엔티티 수정될 때 수정시간 저장하는 컬럼 추가
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
