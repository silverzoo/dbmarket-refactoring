package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.Comment;
import com.example.team1.Prometheus.entity.CommentRequest;
import com.example.team1.Prometheus.entity.CommentResponse;
import com.example.team1.Prometheus.entity.User;
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

    //해당 커멘트 아이디로 조히
    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Transactional
    public CommentResponse updateComment(Long commentId, CommentRequest commentRequest,String userName){


        //조회
        Comment comment = commentRepository.findById(commentId).orElse(null);

        //댓글 작성자의 이름과 세션의 유저이름이 같지않으면 작성 권한이 없음
        if (!comment.getReviewerName().equals(userName)) {
            throw new SecurityException("User not authorized to update this comment");
        }

        //요청 객체 내용 엔티티에 매핑
        comment.setContent(commentRequest.getContent());
        //업데이트된 엔티티 저장
        commentRepository.save(comment);
        //업데이트된 엔티티를 응답 DTO로 반환
        return new CommentResponse(comment);

    }
    //세션 유저 이름이 커멘트의 리뷰어 이름이랑 같으면 삭제
    public void deleteComment(String userName){
        Comment comment = commentRepository.findByReviewerName(userName).orElse(null);

        if (!comment.getReviewerName().equals(userName)) {
            throw new SecurityException("User not authorized to update this comment");
        }
        commentRepository.deleteByReviewerName();}




}
