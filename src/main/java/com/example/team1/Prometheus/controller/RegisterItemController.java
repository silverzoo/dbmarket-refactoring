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
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException {

        log.info("request={}", request);

        //servelet이 제공하는 part로 multipart를 확인
        Collection<Part> parts = request.getParts();

        // 작업을 service로 분리
        log.info("parts={}", parts);
        for (Part part : parts) {
            log.info("==== PART ====");
            log.info("name={}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header {}: {}", headerName,
                        part.getHeader(headerName));
            }
            //편의 메서드
            //content-disposition; filename
            log.info("submittedFileName={}", part.getSubmittedFileName());
            log.info("size={}", part.getSize()); //part body size

            //파일에 저장하기
            if (StringUtils.hasText(part.getSubmittedFileName())) {
                // 저장경로 설정해서 활용
//                String fullPath = fileDir + part.getSubmittedFileName();
                String fullPath = part.getSubmittedFileName();
                log.info("파일 저장 fullPath={}", fullPath);
                part.write(fullPath);
            }
        }
        return "registeritemform";
    }
}
