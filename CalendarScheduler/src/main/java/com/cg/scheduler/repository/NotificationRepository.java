/**
 * 
 */
package com.cg.scheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.scheduler.dto.Employee;
import com.cg.scheduler.dto.Notification;

/**
 * @author Devang
 * created: 04/11/2019
 *
 */
@Repository("notificationRepository")
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	List<Notification> findByToEmp(Employee toEmp);
	
}
