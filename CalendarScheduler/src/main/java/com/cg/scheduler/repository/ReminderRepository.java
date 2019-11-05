/**
 * 
 */
package com.cg.scheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.scheduler.dto.Employee;
import com.cg.scheduler.dto.Reminder;

/**
 * @author Devang
 * created: 04/11/2019
 *
 */

@Repository("reminderRepository")
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

	List<Reminder> findByEmp(Employee emp);
	
}
