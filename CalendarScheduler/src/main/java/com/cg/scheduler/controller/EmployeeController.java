/**
 * 
 */
package com.cg.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.scheduler.dto.Employee;
import com.cg.scheduler.dto.Meeting;
import com.cg.scheduler.dto.Notification;
import com.cg.scheduler.dto.Reminder;
import com.cg.scheduler.exception.EmployeeException;
import com.cg.scheduler.service.EmployeeService;

/**
 * @author Devang created: 05/11/2019
 *
 */

@RestController
@RequestMapping("employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@PostMapping("add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		try {
			return new ResponseEntity<Employee>(employeeService.create(employee), HttpStatus.OK);
		} catch (EmployeeException e) {
			return new ResponseEntity("Error Adding Employee. Please try Again.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("search/email")
	public ResponseEntity<Employee> searchEmployeeByEmail(@RequestParam("empEmail") String email){
		try {
			Employee emp=employeeService.searchByEmail(email);
			for(Reminder rem: emp.getReminders()) {
				rem.setEmp(null);
			}
			for(Meeting meet: emp.getMeetings()) {
				meet.setOrganiser(null);
			}
			for(Notification not: emp.getNotifications()) {
				not.setToEmp(null);
			}
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		} catch (EmployeeException e) {
			return new ResponseEntity("No Employee(s) Found. Please try again with a different search parameter.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("search/name")
	public ResponseEntity<Employee> searchEmployeeByName(@RequestParam("empName") String name){
		try {
			Employee emp=employeeService.searchByName(name);
			for(Reminder rem: emp.getReminders()) {
				rem.setEmp(null);
			}
			for(Meeting meet: emp.getMeetings()) {
				meet.setOrganiser(null);
			}
			for(Notification not: emp.getNotifications()) {
				not.setToEmp(null);
			}
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		} catch (EmployeeException e) {
			return new ResponseEntity("No Employee(s) Found. Please try again with a different search parameter.", HttpStatus.BAD_REQUEST);
		}
	}
	
}
