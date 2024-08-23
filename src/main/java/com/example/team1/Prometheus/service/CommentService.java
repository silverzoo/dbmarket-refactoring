package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.Comment;
import com.example.team1.Prometheus.entity.CommentRequest;
import com.example.team1.Prometheus.entity.CommentResponse;
import com.example.team1.Prometheus.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;


    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    //해당 아이디의 유저 모든 comment 리스트로 리턴
    public List<Comment> getAllCommentById(Long userId){
        return commentRepository.findAllByUser_UserId(userId);}

    //comment id로 해당 댓글 조회
    public Comment getCommentById(Long commentId){
        return commentRepository.findById(commentId).get();
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

    public void deleteComment(Long commentId){commentRepository.deleteById(commentId);}




}
