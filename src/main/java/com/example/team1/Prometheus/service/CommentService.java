package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.Comment;
import com.example.team1.Prometheus.repository.CommentRepository;
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
    public List<Comment> getAllCommentById(Long id){
        return commentRepository.findAllByUser_UserId(id);
    }


}
