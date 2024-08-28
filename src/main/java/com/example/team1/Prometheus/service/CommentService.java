package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.Comment;
import com.example.team1.Prometheus.entity.CommentRequest;
import com.example.team1.Prometheus.entity.CommentResponse;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.exception.NotFoundCommentbyCommentId;
import com.example.team1.Prometheus.exception.NotFoundUserbyUserId;
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

    // 해당 아이디의 유저 모든 comment를 CommentResponse 리스트로 리턴
    public List<CommentResponse> getAllCommentById(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserbyUserId(userId));

        List<Comment> comments = commentRepository.findAllByUser_UserId(userId);

        // Comment를 CommentResponse로 변환
        return comments.stream()
                .map(comment -> new CommentResponse(comment))
                .collect(Collectors.toList());
    }

    //유저의 평점 계산
    public Double ratingAverage(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserbyUserId(userId));

        List<Comment> comments = commentRepository.findAllByUser_UserId(userId);
        if (comments.isEmpty()) {
            return 0.0; // 리뷰가 없는 경우
        }

        double sum = 0;
        for (Comment comment : comments) {
            sum += comment.getRating();
        }

        double average = sum / comments.size();
        return Double.parseDouble(String.format("%.1f", average));
    }

    //유저 평점 퍼센트 계산
    public Double ratingPercentage(Double ratingAverage) {
        double maxRating = 5.0; // 최대 평점
        double percent = (ratingAverage / maxRating) * 100; // 퍼센트 계산
        return Double.parseDouble(String.format("%.1f", percent));
    }


    //해당 커멘트 아이디로 조회
    public CommentResponse getCommentById(long commentId) {
        // Comment를 먼저 조회
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundCommentbyCommentId(commentId));

        // 조회한 Comment로부터 userId를 가져와서 사용
        return new CommentResponse(comment);
    }

    // 댓글 작성
    public CommentResponse createComment(CommentRequest commentRequest, String reviewerName) {

        // User 엔티티를 조회
        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new NotFoundUserbyUserId(commentRequest.getUserId()));

        // 새로운 Comment 엔티티 생성
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setReviewerName(reviewerName);
        comment.setContent(commentRequest.getContent());
        comment.setCreatedAt(commentRequest.getCreatedAt());
        comment.setRating(commentRequest.getRating());

        // Comment 엔티티 저장
        commentRepository.save(comment);

        return new CommentResponse(comment);
    }
    // 댓글 수정
    @Transactional
    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest){
        // 조회
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundCommentbyCommentId(commentId));
        // 요청 객체 내용 엔티티에 매핑
        comment.setContent(commentRequest.getContent());
        // 업데이트된 엔티티 저장
        commentRepository.save(comment);
        // 업데이트된 엔티티를 응답 DTO로 반환
        return new CommentResponse(comment);

    }

    // 댓글 삭제
    public void deleteComment(long commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundCommentbyCommentId(commentId));
        commentRepository.deleteById(commentId);
    }




}