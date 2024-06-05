package com.example.footballnewsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FootballNewsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballNewsServerApplication.class, args);
	}

}
