package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.ItemPostDto;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.property.FileUploadProperties;
import com.example.team1.Prometheus.repository.ItemPostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

// TODO AOP 예외처리 or exception 처리

@Service
@Slf4j
//    file.{변수명} 값 주입, 자료 넘길시 사용
@AllArgsConstructor
public class RegisterItemService {
    private ItemPostRepository itemPostRepository;
    private FileUploadProperties fileUploadProperties;


    //TODO 아이템 업로드할때 쓰는 HttpServletRequest 에도 session이 있는가?
    // throws ServletException, IOException 이미 전역처리했음
    public void uploadItemToDb(ItemPostDto itemPostDto, User user) throws IOException{
        // 작업을 service로 분리
        //TODO  아예 여기에서 문제가 생김(id가 없을 경우) , getSessionUser 예외처리 필요.
        log.info("uploadItemToDb={}", user.getUserId());
//        편의 메서드
        log.info("size={}", itemPostDto.getItemImage().getSize()); //이미지 크기 체크
        log.info("submittedFileName={}", itemPostDto.getItemImage().getOriginalFilename());
        //uuid만 저장하면 uuid 파일이 완성됨, 확장자 필요.
        String extention = itemPostDto.getItemImage().getOriginalFilename()
                .substring(itemPostDto.getItemImage().getOriginalFilename().lastIndexOf("."));
        // 저장경로 설정해서 활용
//                String fullPath = fileDir + itemPostDto.getItemImage().getOriginalFilename();
        log.info("filedir = {}" , fileUploadProperties.getDir());
        String fileName = UUID.randomUUID() + extention;
        // UUID.randomUUID() 또 하면 값이 달라짐!

        String fullPath = "";
        String dbImagePath = "";
        if(fileUploadProperties.getEnvName().equals("dev")){
            fullPath = fileUploadProperties.getRootPath()+ fileUploadProperties.getDir() + fileName;
            //파일 저장하기(static/img 경로)
            //Dto 경로 저장
            dbImagePath = fileUploadProperties.getImagePath() + fileName;
        }else{
            fullPath = fileUploadProperties.getRootPath()+ fileUploadProperties.getDir() + fileName;
            dbImagePath = fileUploadProperties.getDir() + fileName;
        }
        log.info("파일 저장 fullPath={}", fullPath);
        // 저장 경로 생각해보기
        itemPostDto.getItemImage().transferTo(new File(fullPath));
        itemPostRepository.save(itemPostDto.toEntity(user.getUserId(), dbImagePath));
    }
}
// TODO Mapper 사용 Mapstruct 적용

//TODO repository 사용해서 데이터 저장만을 하는 서비스를 구현할 것