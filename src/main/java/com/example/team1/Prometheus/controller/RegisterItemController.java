package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemPostDto;
import com.example.team1.Prometheus.service.RegisterItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
        return "/item/registeritemform";
    }
    @PostMapping
    public String saveFormToDb(@ModelAttribute("itemPostDto") ItemPostDto itemPostDto, HttpServletRequest httpServletRequest) throws IOException {
        //TODO http서블렛 getsession 받아서 service로 넘기기

        // form name=itemInfo하니까 인식됨, 객체로 인식
//        log.info("itemPostDto={}", itemPostDto);
//        log.info("itemInfo={}", itemPostDto.getItemInfo());
//        log.info("itemInfo().getName()={}", itemPostDto.getItemInfo().getName());
//        log.info("itemImage={}", itemPostDto.getItemImage().getOriginalFilename());

        //DB저장작업

        registerItemService.uploadItemToDb(itemPostDto, httpServletRequest);

        //redirection 작업
        return "redirect:/items";
    }
}
