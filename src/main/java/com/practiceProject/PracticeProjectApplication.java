package com.practiceProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PracticeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeProjectApplication.class, args);
	}

}
