package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemPost;
import com.example.team1.Prometheus.entity.ItemPostDto;
import com.example.team1.Prometheus.service.RegisterItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//### 상품등록 페이지(registerForm)
//
//예상 DB: item
//
//- Long item_id PK
//- User user FK
//- ENUM(String) category_name
//- String item_name
//- Integer item_price
//- Image item_image
//- String item_description
//- Date upload_date
//
//registerFormDTO :
//
//상동

// 1. 컨트롤러
// 2. 요청 DTO
// 3. 응답 DTO
// 4. service
// 5. repository
// 6. Mapper SQL 호출하기 위한 인터페이스

//TODO 상품등록 요청이 있을경우 REST컨트롤러 변경
@Controller
@RequestMapping("/registeritem")
public class RegisterItemController {
//    private final RegisterItemService registerItemService;
//    private final RegisterMapper mapper;

    @GetMapping
    public String register() {
        return "registeritemform";
    }
//    @PostMapping("/itemlist")
//    public ResponseEntity postItem(@RequestBody ItemPostDto itemPostDto){
//        ItemPost itemPost = itemPostDto.toEntity();
//        return new ResponseEntity(itemPost, HttpStatus.CREATED);
//    }
//    @PostMapping("/itemlist")
//    public String postItem(@RequestBody ItemPost itemPost){
//        System.out.println(itemPost.toString());
//        return "";
//    }
}
