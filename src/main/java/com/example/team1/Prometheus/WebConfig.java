package com.example.team1.Prometheus;

import com.example.team1.Prometheus.property.FileUploadProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final FileUploadProperties fileUploadProperties;
    //뷰 컨트롤러는 잘 모르겠음
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        WebMvcConfigurer.super.addViewControllers(registry);
//    }


    //이 부분이 없으면 사진 올렸을때 새로고침 안하면 사진 못불러옴
    private String dir;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        dir = fileUploadProperties.getRootPath()+ fileUploadProperties.getDir();

        //클라이언트가 파일에 접근하기 위한 url
        registry.addResourceHandler(fileUploadProperties.getImagePath()+"**")
                .addResourceLocations("file://"+dir);
//        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
