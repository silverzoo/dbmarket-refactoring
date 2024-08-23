package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.service.RegisterItemService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

//TODO 상품등록 요청이 있을경우 REST컨트롤러 변경
@Controller
@RequestMapping("/registeritem")
@Slf4j
@AllArgsConstructor
public class RegisterItemController {
    private final RegisterItemService registerItemService;
//    private final RegisterMapper mapper;

    //file.dir 값 주입, 자료 넘길시 사용
//    @Value("${file.dir}")
//    private String fileDir;
    @GetMapping
    public String register() {
        return "registeritemform";
    }

    @PostMapping
    public String saveFileV1(HttpServletRequest request){

        log.info("request={}", request);

        //DB저장작업
        try {
            registerItemService.uploadItemToDb(request);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //TODO 1 redirection 작업
        return "redirect:/items";
    }
}
