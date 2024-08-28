package com.example.team1.Prometheus.exception.handler;

import com.example.team1.Prometheus.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundItemById.class)
    public String handleNotFoundItemById(NotFoundItemById e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/items";
    }

    @ExceptionHandler(UnauthorizedDeleteByUser.class)
    public String handleUnauthorizedDeleteByUser(UnauthorizedDeleteByUser e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/items";
    }

    @ExceptionHandler(UnauthorizedModifyByUser.class)
    public String handleUnauthorizedModifyByUser(UnauthorizedModifyByUser e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/items";
    }

    @ExceptionHandler(NotFoundUserbyUserId.class)
    public String handleNotFoundUserbyUserId(NotFoundUserbyUserId e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/items";
    }

    @ExceptionHandler(NotFoundCommentbyCommentId.class)
    public String handleNotFoundCommentbyCommentId(NotFoundCommentbyCommentId e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/items";
    }





}
