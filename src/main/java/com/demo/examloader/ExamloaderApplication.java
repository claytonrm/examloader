package com.demo.examloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ExamloaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamloaderApplication.class, args);
	}

}
