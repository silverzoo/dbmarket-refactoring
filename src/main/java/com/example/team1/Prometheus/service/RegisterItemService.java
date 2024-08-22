package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.repository.ItemPostRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collection;


@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterItemService {
    private ItemPostRepository itemPostRepository;
    public void uploadItemToDb(HttpServletRequest request) throws ServletException, IOException {
        // 작업을 service로 분리

        //servelet이 제공하는 part로 multipart를 확인
        Collection<Part> parts = request.getParts();
        log.info("parts={}", parts);
        for (Part part : parts) {
            log.info("==== PART ====");
            log.info("name={}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            //TODO 2 DB저장하기
            for (String headerName : headerNames) {
                log.info("header {}: {}", headerName,
                        part.getHeader(headerName));
            }
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
    }
}
