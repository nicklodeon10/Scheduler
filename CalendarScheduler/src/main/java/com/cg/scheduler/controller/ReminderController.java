/**
 * 
 */
package com.cg.scheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.scheduler.dto.Reminder;
import com.cg.scheduler.exception.EmployeeException;
import com.cg.scheduler.exception.ReminderException;
import com.cg.scheduler.service.EmployeeService;
import com.cg.scheduler.service.ReminderService;

/**
 * @author Devang created: 05/11/2019
 *
 */

@RestController
@RequestMapping("reminder")
@CrossOrigin(origins = "http://localhost:4200")
public class ReminderController {

	@Autowired
	ReminderService reminderService;

	@Autowired
	EmployeeService employeeService;

	@PostMapping("add")
	public ResponseEntity<Reminder> addReminder(@RequestBody Reminder reminder, @RequestParam("empId") Long empId) {
		Reminder addedReminder;
		try {
			reminder.setEmp(employeeService.searchById(empId));
		} catch (EmployeeException exception) {
			return new ResponseEntity("Could not find Employee.", HttpStatus.BAD_REQUEST);
		}
		try {
			addedReminder = reminderService.create(reminder);
		} catch (ReminderException exception) {
			return new ResponseEntity("Could not add reminder. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Reminder>(addedReminder, HttpStatus.OK);
	}
	
	@GetMapping("view")
	public ResponseEntity<List<Reminder>> viewAllReminders(@RequestParam("empId") Long empId){
		List<Reminder> reminderList;
		try {
			reminderList=reminderService.searchByEmpId(empId);
			for(Reminder reminder: reminderList) {
				reminder.getEmp().setReminders(null);
				reminder.getEmp().setNotifications(null);
				reminder.getEmp().setMeetings(null);
			}
		} catch (ReminderException e) {
			return new ResponseEntity("Could not find any reminders. Please try again or add a new one.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Reminder>>(reminderList, HttpStatus.OK);
	}
	
	@GetMapping("upcoming/view")
	public ResponseEntity<List<Reminder>> viewUpcomingReminders(@RequestParam("empId") Long empId){
		List<Reminder> reminderList;
		try {
			reminderList=reminderService.viewUpcoming(empId);
			for(Reminder reminder: reminderList) {
				reminder.getEmp().setReminders(null);
				reminder.getEmp().setNotifications(null);
				reminder.getEmp().setMeetings(null);
			}
		} catch (ReminderException e) {
			return new ResponseEntity("Could not find any reminders. Please try again or add a new one.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Reminder>>(reminderList, HttpStatus.OK);
	}
	
	@DeleteMapping("cancel")
	public ResponseEntity<String> deleteReminder(@RequestParam("remId") Long reminderId) {
		try {
			reminderService.cancel(reminderId);
		} catch (ReminderException e) {
			return new ResponseEntity<String>("Unable to cancel reminder. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Reminder Cancelled.", HttpStatus.OK);
	}
	
	@GetMapping("getCount")
	public ResponseEntity<Integer> getCount(@RequestParam("empId")Long empId){
		try {
			return new ResponseEntity<Integer>(reminderService.upcomingReminderCount(empId),HttpStatus.OK);
		} catch (ReminderException e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
	
}
