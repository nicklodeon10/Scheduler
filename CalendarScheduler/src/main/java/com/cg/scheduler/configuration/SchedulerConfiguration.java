package com.cg.scheduler.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author: DEVANG
 * description: Configuration class for JPA Auditing
 * created date: 10/10/2019
 * modified: -
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SchedulerConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerConfiguration.class);
	
	/*	
	 *  Author: DEVANG
	 *  Description: Configures the JPA Auditor
	 *  Input: -
	 *  Output: AuditorAware object.
	 *  Created Date: 10/10/2019
	 *  Last Modified: -
	 */
	@Bean
	public AuditorAware<String> auditorAware() {
		logger.info("Configuring JPA Auditor.");
		return new AuditorAwareImpl();
	}

}

