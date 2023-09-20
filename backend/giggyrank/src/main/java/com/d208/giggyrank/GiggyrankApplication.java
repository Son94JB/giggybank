package com.d208.giggyrank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GiggyrankApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiggyrankApplication.class, args);
	}

}
