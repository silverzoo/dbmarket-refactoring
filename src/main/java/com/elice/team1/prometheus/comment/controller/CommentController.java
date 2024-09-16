package com.elice.team1.prometheus.comment.controller;

import com.elice.team1.prometheus.comment.dto.CommentRequest;
import com.elice.team1.prometheus.comment.dto.CommentResponse;
import com.elice.team1.prometheus.user.entity.User;
import com.elice.team1.prometheus.category.service.CommentService;
import com.elice.team1.prometheus.config.UserFilter;
import com.elice.team1.prometheus.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 유저의 후기 조회
@RequiredArgsConstructor
@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserFilter userFilter;
    private final UserService userService;

    // 모든 댓글 조회

    @GetMapping("/{userId}")
    public String getAllCommentById(@PathVariable long userId, Model model,
                                    @RequestParam(value ="sort", required = false) String sort,
                                    HttpServletRequest httpServletRequest){
        User reviewer = userFilter.findUserByFilter(model, httpServletRequest);
        List<CommentResponse> comments;
        if ("latest".equals(sort)) {
            //최신순 정렬
            comments = commentService.getAllCommentByIdSorted(userId);
        } else{
            comments = commentService.getAllCommentById(userId);}

        Double ratingAverage = commentService.ratingAverage(userId);
        int roundedStars = (int) Math.round(ratingAverage);  // 평점을 반올림하여 별의 개수로 변환
        Double percentage = commentService.ratingPercentage(ratingAverage); // 퍼센트 계산

        // 댓글 중복검사
        boolean hasCommented = commentService.userHasCommented(userId,reviewer.getUserName());
        if(hasCommented){
            // 이미 리뷰를 작성한 경우 리뷰작성 버튼 숨기기
            model.addAttribute("hasCommented", "hasCommented");
        }

        // 호스트 이름
        User host = userService.findUserById(userId);
        model.addAttribute("host", host.getUserName());

        model.addAttribute("comments",comments);
        model.addAttribute("ratingAverage",ratingAverage);
        model.addAttribute("roundedStars", roundedStars);
        model.addAttribute("percentage", percentage);

        return "comment/comments";
    }

    // 댓글 작성
    @PostMapping("/create")
    public String createComment(@ModelAttribute CommentRequest commentRequest,
                                @RequestParam("reviewerName") String reviewerName) {
        commentService.createComment(commentRequest, reviewerName);
        return "redirect:/comments/" + commentRequest.getUserId();
    }

    // 댓글 수정 페이지 이동
    @GetMapping("edit/{commentId}")
    public String updateComment(@PathVariable("commentId") Long commentId, Model model){
        CommentResponse comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "comment/edit";
    }


    // 수정 후 댓글 목록 페이지로 리다이렉트
    @PostMapping("/edit/{commentId}")
    public String updateComment(@PathVariable("commentId") Long commentId,
                                @ModelAttribute CommentRequest commentRequest) {

        // 댓글 수정 서비스 호출
        commentService.updateComment(commentId, commentRequest);
        // 수정 후 댓글 목록 페이지로 리디렉션
        return "redirect:/comments/" + commentRequest.getUserId();
    }

    @DeleteMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId) {
        Long userId = commentService.deleteComment(commentId);

        return "redirect:/comments/" + userId;
    }

    



}
