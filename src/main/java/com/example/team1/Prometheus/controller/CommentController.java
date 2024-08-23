package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.Comment;
import com.example.team1.Prometheus.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//유저의 후기 조회
@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping("/{userid}")
    public String getAllCommentById(@PathVariable Long userid, Model model){
        List<Comment> comments = commentService.getAllCommentById(userid);
        model.addAttribute("comments",comments);
        return "comments";
    }
}
