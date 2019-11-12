/**
 * 
 */
package com.cg.scheduler.controller;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.scheduler.configuration.JwtTokenUtil;
import com.cg.scheduler.dto.Employee;
import com.cg.scheduler.dto.JwtRequest;
import com.cg.scheduler.dto.JwtResponse;
import com.cg.scheduler.exception.EmployeeException;
import com.cg.scheduler.service.EmployeeService;
import com.cg.scheduler.service.UserDetailsServiceImpl;

/**
 * @author Devang created: 05/11/2019
 *
 */

@RestController
@RequestMapping("employee")
@CrossOrigin(origins = "http://13.233.124.218:4200")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	//Generates and retrieves token
	@PostMapping("/authenticate")
	@PermitAll
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		logger.info("Generating Token");
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	//Checks if user is authenticated or not
	private void authenticate(String username, String password) throws Exception {
		try {
			logger.info("Authenticating");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			logger.info("Auth Error");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			logger.info("Auth Error");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	//Returns the role of a user
	@GetMapping("/getRole")
	public ResponseEntity<Employee> getRole(@RequestParam("username") String username){
		try {
			logger.info("Finding User Role");
			Employee user=employeeService.searchByUsername(username);
			user.setEmpPassword("******");
			logger.info("Returning User for role");
			user.setMeetings(null);
			user.setNotifications(null);
			user.setReminders(null);
			return new ResponseEntity<Employee>(user, HttpStatus.OK);
		} catch (EmployeeException e) {
			logger.info("Error in fetching");
			return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Add a Employee
	@PostMapping("add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		try {
			logger.info("Adding Employee");
			employee.setRoles("ROLE_USER");
			return new ResponseEntity<Employee>(employeeService.create(employee), HttpStatus.OK);
		} catch (EmployeeException e) {
			logger.error("Error Adding Employee. Please try Again.");
			return new ResponseEntity("Error Adding Employee. Please try Again.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Search by Email
	@GetMapping("search/email")
	public ResponseEntity<Employee> searchEmployeeByEmail(@RequestParam("empEmail") String email){
		Employee emp;
		try {
			logger.info("Searching");
			emp=employeeService.searchByEmail(email);
			emp.setMeetings(null);
			emp.setNotifications(null);
			emp.setReminders(null);
			return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		} catch (EmployeeException e) {
			logger.error("No Employee(s) Found.");
			return new ResponseEntity("No Employee(s) Found. Please try again with a different search parameter.", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Search By Name
	@GetMapping("search/name")
	public ResponseEntity<List<Employee>> searchEmployeeByName(@RequestParam("empName") String name){
		List<Employee> empList;
		try {
			logger.info("Searching.");
			empList=employeeService.searchByName(name);
			for(Employee emp: empList) {
				emp.setMeetings(null);
				emp.setNotifications(null);
				emp.setReminders(null);
			}
			return new ResponseEntity<List<Employee>>(empList, HttpStatus.OK);
		} catch (EmployeeException e) {
			logger.error("No Employee(s) Found.");
			return new ResponseEntity("No Employee(s) Found. Please try again with a different search parameter.", HttpStatus.BAD_REQUEST);
		}
	}
	
}
