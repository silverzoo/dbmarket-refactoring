package com.elice.team1.dbmarket.user.entity;

import com.elice.team1.dbmarket.comment.entity.Comment;
import com.elice.team1.dbmarket.item.entity.Item;
import jakarta.persistence.*;
import lombok.*;

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
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Item> items;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Column(nullable = false)
    private String username;

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
    public User(String username, String password, Double rate) {
        this.username = username;
        this.password = password;
        this.rate = rate;
    }
}

