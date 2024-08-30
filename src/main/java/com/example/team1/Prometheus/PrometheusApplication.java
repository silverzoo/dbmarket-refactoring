package com.example.team1.Prometheus;

import com.example.team1.Prometheus.property.FileUploadProperties;
import com.example.team1.Prometheus.service.RegisterItemService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan
//Consider defining a bean of type 'java.lang.String' in your configuration. 버그생김
@EnableConfigurationProperties({
		FileUploadProperties.class
})
public class PrometheusApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrometheusApplication.class, args);
	}

}