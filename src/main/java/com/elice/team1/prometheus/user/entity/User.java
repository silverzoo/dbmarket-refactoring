package com.elice.team1.prometheus.user.entity;

import com.elice.team1.prometheus.comment.entity.Comment;
import com.elice.team1.prometheus.item.entity.Item;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Setter
@ToString
@Getter
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Item> items;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

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

    @Builder
    public User(String userName, String password, Double rate) {
        this.userName = userName;
        this.password = password;
        this.rate = rate;
    }
}

