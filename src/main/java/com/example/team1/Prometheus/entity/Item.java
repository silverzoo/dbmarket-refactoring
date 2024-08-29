package com.example.team1.Prometheus.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name="item")
@Entity
@Getter
@Builder
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

    //TODO ConstraintViolationException 처리 => pattrn, notblank 둘 다 처리됨
    @NotBlank(message = "상품 이름은 공백으로 두시면 안됩니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]+[가-힣a-zA-Z0-9\\s]*$",message = "상품 이름을 제대로 등록해주세요.")
    @Column(name = "name", nullable = false)
    private String name;

//    @NotBlank(message = "가격을 제대로 입력해주세요.") integer에선 적용 X
    // ConstraintViolationException:
    @Max(value = 2147483646,message = "가격은 2147483646원을 넘을 수 없습니다.")
    @Min(value = 10 , message = "가격은 10원부터 시작합니다.")
    @Column(name = "price", nullable = false)
    private int price;

    @JoinColumn(name = "category_category_id")
    private Long categoryId;

    @NotNull(message = "카테고리를 제대로 등록해주세요.")
    @Column(name = "category", nullable = false)
    private String category;

    //itemPostDto 에서 validate
    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @NotBlank(message = "내용을 입력해주세요.")
    @Column(name = "description")
    private String description;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
