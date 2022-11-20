package com.ssafy.nuguri;

import org.joda.time.DateTimeUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@SpringBootApplication
public class NuguriApplication {

	public static void main(String[] args) {
		SpringApplication.run(NuguriApplication.class, args);
	}

}
