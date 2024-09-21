package com.elice.team1.dbmarket.item.property;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "upload")
public class FileUploadProperties {
    public FileUploadProperties(){}
//    @Value("${upload.envName}")
    private String envName;
//    @Value("${upload.imagePath}")
    private String imagePath;
    private final String rootPath  = System.getProperty("user.dir");
//    @Value("${upload.dir}")
    private String dir;
}
