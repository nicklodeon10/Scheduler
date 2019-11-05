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
import com.cg.scheduler.exception.ReminderException;
import com.cg.scheduler.repository.EmployeeRepository;
import com.cg.scheduler.repository.ReminderRepository;

/**
 * @author Devang created: 04/11/2019
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
	public Reminder create(Reminder reminder) throws ReminderException {
		Reminder addedReminder;
		try {
			addedReminder=reminderRepository.save(reminder);
		}catch (Exception exception) {
			throw new ReminderException("Error Adding Reminder.");
		}
		return addedReminder;
	}

	@Override
	public List<Reminder> read() throws ReminderException {
		List<Reminder> reminderList=reminderRepository.findAll();
		if(reminderList.size()==0) {
			throw new ReminderException("No Reminders Found.");
		}
		return reminderList;
	}

	@Override
	public Reminder update(Reminder reminder) throws ReminderException {
		Reminder updatedReminder;
		try {
			updatedReminder=reminderRepository.save(reminder);
		}catch (Exception exception) {
			throw new ReminderException("Error Updating Reminder.");
		}
		return updatedReminder;
	}

	@Override
	public boolean cancel(Long remId) throws ReminderException {
		try {
			Reminder reminder = reminderRepository.findById(remId).get();
			reminder.setActive(false);
			reminderRepository.save(reminder);
		}catch(Exception exception) {
			throw new ReminderException("Error Cancelling Reminder.");
		}
		return true;
	}

	@Override
	public List<Reminder> searchByEmpId(Long empId) throws ReminderException {
		List<Reminder> reminderList=reminderRepository.findByEmp(employeeRepository.findById(empId).get());
		if(reminderList.size()==0) {
			throw new ReminderException("No Reminders Found.");
		}
		return reminderList;
	}

	@Override
	public List<Reminder> viewUpcoming(Long empId) throws ReminderException {
		List<Reminder> reminderList = searchByEmpId(empId);
		for (Reminder reminder : reminderList) {
			if (reminder.getDueTime().isBefore(LocalDateTime.now())) {
				reminderList.remove(reminder);
			}
		}
		return reminderList;
	}

}
