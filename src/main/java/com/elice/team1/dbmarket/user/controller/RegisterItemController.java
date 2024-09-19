package com.elice.team1.dbmarket.user.controller;

import com.elice.team1.dbmarket.item.entity.Item;
import com.elice.team1.dbmarket.item.dto.ItemPostDto;
import com.elice.team1.dbmarket.user.entity.User;
import com.elice.team1.dbmarket.item.service.RegisterItemService;
import com.elice.team1.dbmarket.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
    private final HttpServletResponse httpServletResponse;
    UserService userService;
//    private final RegisterMapper mapper;

    @GetMapping
    public String register(HttpServletRequest httpServletRequest) {
        User user =userService.getSessionUser(httpServletRequest);
        log.info("UserSession={}",user);
        return "item/registeritemform";
    }
    @PostMapping
    public String saveFormToDb(@ModelAttribute("itemPostDto") @Valid ItemPostDto itemPostDto, HttpServletRequest httpServletRequest) throws IOException {
        //TODO http서블렛 getsession 받아서 service로 넘기기

        // form name=itemInfo하니까 인식됨, 객체로 인식
//        log.info("itemPostDto={}", itemPostDto);
//        log.info("itemInfo={}", itemPostDto.getItemInfo());
//        log.info("itemInfo().getName()={}", itemPostDto.getItemInfo().getName());
//        log.info("itemImage={}", itemPostDto.getItemImage().getOriginalFilename());

        //DB저장작업
        User user =userService.getSessionUser(httpServletRequest);
        log.info("UserSession={}",user);

        Item item = registerItemService.uploadItemToDb(itemPostDto, user);

        //redirection 작업
        return "redirect:category/" + item.getCategory();
    }
}
