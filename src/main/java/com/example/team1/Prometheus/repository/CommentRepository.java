package com.example.team1.Prometheus.repository;

import com.example.team1.Prometheus.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser_UserId(Long userId);
    List<Comment> findAllByReviewerName(String reviewerName);
    List<Comment> findAllByUser_UserIdOrderByCreatedAtDesc(Long userId);
}
