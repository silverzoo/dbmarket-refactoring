package com.elice.team1.prometheus.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@NoArgsConstructor

@Setter
@ToString
@Getter
@Entity
@Table
public class User {
    @Id
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Double rate;

    @PrePersist
    public void prePersist() {
        if (this.rate == null) {
            this.rate = 0.0;
        }
    }


}

