package com.cg.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cg.scheduler.service.UserDetailsServiceImpl;

@SpringBootApplication
public class CalendarSchedulerApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(CalendarSchedulerApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Application");
		SpringApplication.run(CalendarSchedulerApplication.class, args);
		logger.info("Application Running");
	}

}
