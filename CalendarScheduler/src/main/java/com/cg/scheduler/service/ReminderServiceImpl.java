/**
 * 
 */
package com.cg.scheduler.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.scheduler.dto.Reminder;
import com.cg.scheduler.repository.EmployeeRepository;
import com.cg.scheduler.repository.ReminderRepository;

/**
 * @author Devang
 * created: 04/11/2019
 *
 */

@Service("reminderService")
@Transactional
public class ReminderServiceImpl implements ReminderService {
	
	@Autowired
	ReminderRepository reminderRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Reminder create(Reminder reminder) {
		return reminderRepository.save(reminder);
	}

	@Override
	public List<Reminder> read() {
		return reminderRepository.findAll();
	}

	@Override
	public Reminder update(Reminder reminder) {
		return reminderRepository.save(reminder);
	}

	@Override
	public boolean cancel(Long remId) {
		Reminder reminder=reminderRepository.findById(remId).get();
		reminder.setActive(false);
		reminderRepository.save(reminder);
		return true;
	}

	@Override
	public List<Reminder> searchByEmpId(Long empId) {
		return reminderRepository.findByEmp(employeeRepository.findById(empId).get());
	}

	@Override
	public List<Reminder> viewUpcoming(Long empId) {
		List<Reminder> reminderList=searchByEmpId(empId);
		for(Reminder reminder: reminderList) {
			if(reminder.getDueTime().isBefore(LocalDateTime.now())) {
				reminderList.remove(reminder);
			}
		}
		return reminderList;
	}

}
