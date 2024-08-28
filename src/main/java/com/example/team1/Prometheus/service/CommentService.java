package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.Comment;
import com.example.team1.Prometheus.entity.CommentRequest;
import com.example.team1.Prometheus.entity.CommentResponse;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.exception.NotFoundCommentbyCommentId;
import com.example.team1.Prometheus.exception.NotFoundUserbyUserId;
import com.example.team1.Prometheus.mapper.CommentMapper;
import com.example.team1.Prometheus.repository.CommentRepository;
import com.example.team1.Prometheus.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    // 해당 아이디의 유저 모든 comment를 CommentResponse 리스트로 리턴
    public List<CommentResponse> getAllCommentById(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserbyUserId(userId));

        List<Comment> comments = commentRepository.findAllByUser_UserId(userId);

        // Comment를 CommentResponse로 변환
        return comments.stream()
                .map(commentMapper::toResponse)
                .collect(Collectors.toList());
    }

    // 유저의 평점 계산
    public Double ratingAverage(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserbyUserId(userId));

        List<Comment> comments = commentRepository.findAllByUser_UserId(userId);
        if (comments.isEmpty()) {
            return 0.0; // 리뷰가 없는 경우
        }

        double sum = comments.stream()
                .mapToDouble(Comment::getRating)
                .sum();

        double average = sum / comments.size();
        return Double.parseDouble(String.format("%.1f", average));
    }

    // 유더 평점 업데이트
    private void updateUserRating(long userId) {
        // ratingAverage 메서드를 사용하여 평균 평점을 계산
        Double averageRating = ratingAverage(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserbyUserId(userId));
        user.setRating(averageRating);

        userRepository.save(user);
    }

    // 유저 평점 퍼센트 계산
    public Double ratingPercentage(Double ratingAverage) {
        double maxRating = 5.0; // 최대 평점
        double percent = (ratingAverage / maxRating) * 100; // 퍼센트 계산
        return Double.parseDouble(String.format("%.1f", percent));
    }

    // 해당 커멘트 아이디로 조회
    public CommentResponse getCommentById(long commentId) {
        // Comment를 먼저 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentbyCommentId(commentId));

        // 조회한 Comment로부터 userId를 가져와서 사용
        return commentMapper.toResponse(comment);
    }

    // 댓글 작성
    public CommentResponse createComment(CommentRequest commentRequest, String reviewerName) {

        // User 엔티티를 조회
        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new NotFoundUserbyUserId(commentRequest.getUserId()));

        // CommentRequest를 Comment 엔티티로 변환
        Comment comment = commentMapper.toEntity(commentRequest);
        comment.setUser(user);
        comment.setReviewerName(reviewerName);

        // Comment 엔티티 저장
        Comment savedComment = commentRepository.save(comment);
        updateUserRating(user.getUserId());
        return commentMapper.toResponse(savedComment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest) {
        // 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentbyCommentId(commentId));

        // 요청 객체 내용 엔티티에 매핑
        comment.setContent(commentRequest.getContent());
        comment.setRating(commentRequest.getRating()); // 필요한 경우

        // 업데이트된 엔티티 저장
        Comment updatedComment = commentRepository.save(comment);
        updateUserRating(comment.getUser().getUserId());

        return commentMapper.toResponse(updatedComment);
    }

    // 댓글 삭제
    public Long deleteComment(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentbyCommentId(commentId));
        Long userId = comment.getUser().getUserId();
        commentRepository.delete(comment);
        updateUserRating(userId);
        return userId;
    }
}
