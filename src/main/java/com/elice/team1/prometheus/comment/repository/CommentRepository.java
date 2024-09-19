package com.elice.team1.prometheus.comment.repository;

import com.elice.team1.prometheus.comment.entity.Comment;
import com.elice.team1.prometheus.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser(User user);
    List<Comment> findAllByReviewerName(String reviewerName);
    List<Comment> findAllByUserOrderByCreatedAtDesc(User user);
}
