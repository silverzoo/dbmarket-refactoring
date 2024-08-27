package com.example.team1.Prometheus.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name="item")
@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Valid
@EntityListeners(AuditingEntityListener.class)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "item_id", updatable = false)
    private Long itemId;

    //TODO user 와 joincolumn user_id 사용하도록
    @Column(name = "user_id" , nullable = false)
    private Long userId;

//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    User user;

    @NotEmpty(message = "상품 이름을 제대로 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]+[가-힣a-zA-Z0-9\\s]*$",message = "상품 이름을 제대로 등록해주세요.")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 10 , max = 2147483646, message = "가격은 2147483646원을 넘을 수 없습니다.")
    @NotEmpty(message = "가격을 제대로 입력해주세요.")
    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "category", nullable = false)
    private String category;

    @NotEmpty(message = "이미지를 등록해주세요.")
    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @NotEmpty(message = "내용을 입력해주세요.")
    @Column(name = "description")
    private String description;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
