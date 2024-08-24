package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.Comment;
import com.example.team1.Prometheus.entity.CommentRequest;
import com.example.team1.Prometheus.entity.CommentResponse;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    // 해당 아이디의 유저 모든 comment를 CommentResponse 리스트로 리턴
    public List<CommentResponse> getAllCommentById(Long userId) {
        List<Comment> comments = commentRepository.findAllByUser_UserId(userId);

        // Comment를 CommentResponse로 변환
        return comments.stream()
                .map(comment -> new CommentResponse(comment))
                .collect(Collectors.toList());
    }

    //해당 커멘트 아이디로 조회
    public CommentResponse getCommentById(Long commentId) {
        return new CommentResponse(commentRepository.findById(commentId).orElse(null));
    }

    @Transactional
    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest){
        //조회
        Comment comment = commentRepository.findById(commentId).orElse(null);

        //요청 객체 내용 엔티티에 매핑
        comment.setContent(commentRequest.getContent());
        //업데이트된 엔티티 저장
        commentRepository.save(comment);
        //업데이트된 엔티티를 응답 DTO로 반환
        return new CommentResponse(comment);

    }


    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }




}
