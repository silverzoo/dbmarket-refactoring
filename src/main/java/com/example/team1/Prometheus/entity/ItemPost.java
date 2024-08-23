package com.example.team1.Prometheus.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import java.time.LocalDateTime;


@Table(name="itemtemp")
@Entity
@AllArgsConstructor
@Getter
@Builder
public class ItemPost{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", updatable = false)
    private Long itemId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "imagepath", nullable = false)
    private String imagePath;

    @Column(name = "description")
    private String description;

    //엔티티 생성될 때 발행시간 저장하는 컬럼 추가
    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
