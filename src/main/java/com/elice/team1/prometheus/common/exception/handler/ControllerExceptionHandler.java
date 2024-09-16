package com.elice.team1.prometheus.common.exception.handler;

import com.elice.team1.prometheus.common.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundItemById.class)
    public String handleNotFoundItemById(NotFoundItemById e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/categories";
    }

    @ExceptionHandler(UnauthorizedDeleteByUser.class)
    public String handleUnauthorizedDeleteByUser(UnauthorizedDeleteByUser e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/categories";
    }

    @ExceptionHandler(UnauthorizedModifyByUser.class)
    public String handleUnauthorizedModifyByUser(UnauthorizedModifyByUser e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/categories";
    }

    @ExceptionHandler(UnauthorizedCreateByUser.class)
    public String handleUnauthorizedCreateByUser(UnauthorizedModifyByUser e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/categories";
    }

    @ExceptionHandler(NotFoundUserbyUserId.class)
    public String handleNotFoundUserbyUserId(NotFoundUserbyUserId e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/categories";
    }

    @ExceptionHandler(NotFoundCommentbyCommentId.class)
    public String handleNotFoundCommentbyCommentId(NotFoundCommentbyCommentId e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/category/1";
    }





}
