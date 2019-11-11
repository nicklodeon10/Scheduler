package com.cg.scheduler.configuration;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;

/**
 * @author: DEVANG
 * description: Provides the current user name to the Auditor 
 * created date: 09/10/2019
 * modified: -
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	private static final Logger logger = LoggerFactory.getLogger(AuditorAwareImpl.class);
	
	/*	
	 *  Author: DEVANG
	 *  Description: Provides a String Optional for the current user.
	 *  Input: -
	 *  Output: Optional<String>
	 *  Created Date: 09/10/2019
	 *  Last Modified: -
	 */
	@Override
	public Optional<String> getCurrentAuditor() {
		logger.info("Returning optional string of current auditor.");
		return Optional.of("DEVANG");
	}

}
