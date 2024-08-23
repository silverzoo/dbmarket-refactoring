package com.example.team1.Prometheus.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Table(name="comment")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    private String reviewerName;
    private String content;
    private Timestamp createdAt;
}

