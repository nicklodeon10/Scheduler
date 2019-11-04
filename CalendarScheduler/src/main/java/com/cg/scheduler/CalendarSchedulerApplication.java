package com.cg.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalendarSchedulerApplication {

	public static void main(String[] args) {
		System.out.println("Starting Application");
		SpringApplication.run(CalendarSchedulerApplication.class, args);
		System.out.println("Application Running");
	}

}
