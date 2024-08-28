package com.example.team1.Prometheus.property;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix="file")
public class FileUploadProperties {
    private final String envName;
    private final String imagePath;
    private final String rootPath = System.getProperty("user.dir");
    private final String dir;
}
