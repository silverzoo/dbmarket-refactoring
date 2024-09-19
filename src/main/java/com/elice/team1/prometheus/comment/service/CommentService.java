package com.elice.team1.prometheus.comment.service;

import com.elice.team1.prometheus.comment.entity.Comment;
import com.elice.team1.prometheus.comment.dto.CommentRequest;
import com.elice.team1.prometheus.comment.dto.CommentResponse;
import com.elice.team1.prometheus.user.entity.User;
import com.elice.team1.prometheus.common.exception.NotFoundCommentbyCommentId;
import com.elice.team1.prometheus.common.exception.NotFoundUserbyUserId;
import com.elice.team1.prometheus.comment.mapper.CommentMapper;
import com.elice.team1.prometheus.comment.repository.CommentRepository;
import com.elice.team1.prometheus.user.repository.UserRepository;
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
    public List<CommentResponse> getAllCommentById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserbyUserId(userId));

        List<Comment> comments = commentRepository.findAllByUser(user);

        // Comment를 CommentResponse로 변환
        return comments.stream()
                .map(commentMapper::toResponse)
                .collect(Collectors.toList());
    }

    // 유저의 평점 계산
    public Double ratingAverage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserbyUserId(userId));

        List<Comment> comments = commentRepository.findAllByUser(user);
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
    private void updateUserRating(Long userId) {
        // ratingAverage 메서드를 사용하여 평균 평점을 계산
        Double averageRating = ratingAverage(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserbyUserId(userId));
        user.setRate(averageRating);

        userRepository.save(user);
    }


    // 유저 평점 퍼센트 계산
    public Double ratingPercentage(Double ratingAverage) {
        double maxRating = 5.0; // 최대 평점
        double percent = (ratingAverage / maxRating) * 100; // 퍼센트 계산
        return Double.parseDouble(String.format("%.1f", percent));
    }

    // 해당 커멘트 아이디로 조회
    public CommentResponse getCommentById(Long commentId) {
        // Comment를 먼저 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentbyCommentId(commentId));

        // 조회한 Comment로부터 userId를 가져와서 사용
        return commentMapper.toResponse(comment);
    }

    // 댓글 작성
    public CommentResponse createComment(CommentRequest commentRequest, String reviewerName) {

        // User 엔티티를 조회
        User user = userRepository.findById(commentRequest.getUser().getId())
                .orElseThrow(() -> new NotFoundUserbyUserId(commentRequest.getUser().getId()));

        Comment comment = commentMapper.toEntity(commentRequest);
        comment.setUser(user);
        comment.setReviewerName(reviewerName);

        // Comment 엔티티 저장
        Comment savedComment = commentRepository.save(comment);
        updateUserRating(user.getId());
        return commentMapper.toResponse(savedComment);
    }

    // 댓글 수정
    @Transactional
    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest) {
        // 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentbyCommentId(commentId));

        comment.setContent(commentRequest.getContent());
        comment.setRating(commentRequest.getRating());

        // 업데이트된 엔티티 저장
        Comment updatedComment = commentRepository.save(comment);
        updateUserRating(comment.getUser().getId());

        return commentMapper.toResponse(updatedComment);
    }

    // 댓글 삭제
    public Long deleteComment(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentbyCommentId(commentId));
        Long userId = comment.getUser().getId();
        commentRepository.delete(comment);
        updateUserRating(userId);
        return userId;

    }

    public boolean userHasCommented(Long hostId, String reviewerName) {
        List<CommentResponse> comments = getAllCommentById(hostId);
        for (CommentResponse comment : comments) {
            if (comment.getReviewerName().equals(reviewerName)) {
                return true;
            }
        }
        return false;
    }

    //댓글 최신순 정렬
    public List<CommentResponse> getAllCommentByIdSorted(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserbyUserId(userId));
        List<Comment> comments = commentRepository.findAllByUserOrderByCreatedAtDesc(user);
        return comments.stream()
                .map(commentMapper::toResponse)
                .collect(Collectors.toList());

    }
}
