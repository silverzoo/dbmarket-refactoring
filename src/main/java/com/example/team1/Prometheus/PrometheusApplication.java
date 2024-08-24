package com.example.team1.Prometheus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PrometheusApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrometheusApplication.class, args);
	}

}
