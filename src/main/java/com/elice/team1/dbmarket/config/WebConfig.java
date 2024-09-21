package com.elice.team1.dbmarket.config;

import com.elice.team1.dbmarket.item.property.FileUploadProperties;
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


    //REFACTOR: 파일 업로드 경로 확인 필요
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String imagePath = fileUploadProperties.getImagePath();
        String dir = fileUploadProperties.getDir();

        if (fileUploadProperties.getEnvName().equals("dev")) {
            //macOS에서는 `file:/`을 사용
            registry.addResourceHandler(STR."\{imagePath}**")
                    .addResourceLocations(STR."file:\{dir}/");
        } else {
            registry.addResourceHandler(STR."\{imagePath}**")
                    .addResourceLocations(STR."file:\{dir}/");
        }
    }

}
