package com.mp.StatusTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StatusTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatusTrackerApplication.class, args);
	}

}
