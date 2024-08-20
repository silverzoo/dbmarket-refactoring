package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.Comment;
import com.example.team1.Prometheus.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }


    @GetMapping("/{userId}")
    public String getAllCommentById(@PathVariable Long userId, Model model){
        List<Comment> comments = commentService.getAllCommentById(userId);
        model.addAttribute("comments",comments);
        model.addAttribute("userId", userId);
        return "comments";
    }
}
