package com.elice.team1.dbmarket;

import com.elice.team1.dbmarket.item.property.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties(FileUploadProperties.class)
public class DbmarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbmarketApplication.class, args);
	}

}