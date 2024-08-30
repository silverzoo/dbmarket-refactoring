package com.example.team1.Prometheus.property;


import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;



//Consider defining a bean of type 'java.lang.String' in your configuration. 버그생김


//@Component
//@AllArgsConstructor
@Component
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "upload")
public class FileUploadProperties {
    public FileUploadProperties(){
    }
    //Parameter 0 of constructor in com.example.team1.Prometheus.property.
    //스프링 bean 아니라 의존성 자동 주입이 되지 않음 ㅡ,.ㅡ
//    @Value("${upload.envName}")
    private String envName;
//    @Value("${upload.imagePath}")
    private String imagePath;
    private final String rootPath  = System.getProperty("user.dir");
//    @Value("${upload.dir}")
    private String dir;
}
