package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.ItemPostDto;
import com.example.team1.Prometheus.repository.ItemPostRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class RegisterItemService {
    private ItemPostRepository itemPostRepository;
    private ItemPostDto itemPostDto;
    //TODO 아이템 업로드할때 쓰는 HttpServletRequest 에도 session이 있는가?
    private UserService userService;

    public void uploadItemToDb(HttpServletRequest request) throws ServletException, IOException {
        // 작업을 service로 분리

//        public Member createMember(Member member) {
//            return memberRepository.save(member);
//        }
        //servelet이 제공하는 part로 multipart를 확인
        Collection<Part> parts = request.getParts();
        log.info("parts={}", parts);
        for (Part part : parts) {
            log.info("==== PART ====");
            //데이터 읽기
            log.info("name={}", part.getName());
            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//            log.info("body={}", body);
            //TODO 2 DB저장하기
            //TODO 3 리팩토링
            switch (part.getName()) {
                case "name":
                    itemPostDto.setName(body);
                    break;
                case "price":
                    itemPostDto.setPrice(Integer.parseInt(body));
                    break;
                case "category":
                    itemPostDto.setCategory(body);
                    break;
                case "imgFile":

                    if (StringUtils.hasText(part.getSubmittedFileName())) {
                        //편의 메서드
                        log.info("size={}", part.getSize()); //part body size
                        log.info("submittedFileName={}", part.getSubmittedFileName());
                        //uuid만 저장하면 uuid 파일이 완성됨, 확장자 필요.
                        String extention = part.getSubmittedFileName()
                                .substring(part.getSubmittedFileName().lastIndexOf("."));
                        // 저장경로 설정해서 활용
//                String fullPath = fileDir + part.getSubmittedFileName();
                        String fullPath = UUID.randomUUID().toString() + extention;
//                        log.info("파일 저장 fullPath={}", fullPath);
                        //파일 저장하기
                        part.write(fullPath);
                        //Dto 경로 저장
                        itemPostDto.setImagePath(fullPath);
                    }
                    break;
                case "description":
                    itemPostDto.setDescription(body);
                    break;
            }
        }
        itemPostDto.setUserId(userService.getSession(request));
        itemPostRepository.save(itemPostDto.toEntity());
    }
}
