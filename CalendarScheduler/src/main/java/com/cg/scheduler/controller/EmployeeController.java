/**
 * 
 */
package com.cg.scheduler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.scheduler.dto.Employee;
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
	public ResponseEntity<Employee> addEmployee(@ModelAttribute Employee employee){
		try {
			return new ResponseEntity<Employee>(employeeService.create(employee), HttpStatus.OK);
		} catch (EmployeeException e) {
			return new ResponseEntity("Error Adding Employee. Please try Again.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("search/email")
	public ResponseEntity<Employee> searchEmployeeByEmail(@RequestParam("empEmail") String email){
		try {
			return new ResponseEntity<Employee>(employeeService.searchByEmail(email), HttpStatus.OK);
		} catch (EmployeeException e) {
			return new ResponseEntity("No Employee(s) Found. Please try again with a different search parameter.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("search/name")
	public ResponseEntity<Employee> searchEmployeeByName(@RequestParam("empName") String name){
		try {
			return new ResponseEntity<Employee>(employeeService.searchByName(name), HttpStatus.OK);
		} catch (EmployeeException e) {
			return new ResponseEntity("No Employee(s) Found. Please try again with a different search parameter.", HttpStatus.BAD_REQUEST);
		}
	}
	
}
