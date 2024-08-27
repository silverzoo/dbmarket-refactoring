package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.ItemPostDto;
import com.example.team1.Prometheus.repository.ItemPostRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

// TODO AOP 예외처리 확인
@ControllerAdvice
@Service
@AllArgsConstructor
@Slf4j
public class RegisterItemService {
    private ItemPostRepository itemPostRepository;
    //루트 경로 불러오기
    private final String rootPath = System.getProperty("user.dir");
    // 프로젝트 루트 경로에 있는 img 디렉토리
    private final String fileDir = rootPath + "/src/main/resources/static/upload/";
    //TODO 아이템 업로드할때 쓰는 HttpServletRequest 에도 session이 있는가?
    private UserService userService;

    // throws ServletException, IOException 이미 전역처리했음
    public void uploadItemToDb(ItemPostDto itemPostDto, HttpServletRequest httpServletRequest) throws IOException{
        // 작업을 service로 분리
        itemPostDto.getItemInfo().setUserId(userService.getSession(httpServletRequest));
//        편의 메서드
        log.info("size={}", itemPostDto.getItemImage().getSize()); //이미지 크기 체크
        log.info("submittedFileName={}", itemPostDto.getItemImage().getOriginalFilename());
        //uuid만 저장하면 uuid 파일이 완성됨, 확장자 필요.
        String extention = itemPostDto.getItemImage().getOriginalFilename()
                .substring(itemPostDto.getItemImage().getOriginalFilename().lastIndexOf("."));
        // 저장경로 설정해서 활용
//                String fullPath = fileDir + itemPostDto.getItemImage().getOriginalFilename();
        log.info("filedir = {}" , fileDir);
        String fileName = UUID.randomUUID() + extention;
        // UUID.randomUUID() 또 하면 값이 달라짐!
        String fullPath = fileDir + fileName;
        log.info("파일 저장 fullPath={}", fullPath);
        //파일 저장하기(static/img 경로)
        //Dto 경로 저장
        itemPostDto.getItemInfo().setImagePath("/upload/"+fileName);
        // TODO 저장 경로 생각해보기
        itemPostDto.getItemImage().transferTo(new File(fullPath));
        itemPostRepository.save(itemPostDto.toEntity());
    }
}

//TODO repository 사용해서 데이터 저장만을 하는 서비스를 구현할 것