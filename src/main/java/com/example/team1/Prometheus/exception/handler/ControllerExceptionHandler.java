package com.example.team1.Prometheus.exception.handler;

import com.example.team1.Prometheus.exception.NotFoundUserException;
import com.example.team1.Prometheus.exception.NotFoundItemById;
import com.example.team1.Prometheus.exception.UnauthorizedDeleteByUser;
import com.example.team1.Prometheus.exception.UnauthorizedModifyByUser;
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

    @ExceptionHandler(NotFoundUserException.class)
    public String handleNonFoundUserException(NotFoundUserException e, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/home";
    }
}
