/**
 * 
 */
package com.cg.scheduler.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.scheduler.dto.Notification;
import com.cg.scheduler.exception.NotificationException;
import com.cg.scheduler.service.NotificationService;

/**
 * @author Devang created: 05/11/2019
 *
 */

@RestController
@RequestMapping("notification")
@CrossOrigin(origins = "http://13.233.124.218:4200")
public class NotificationController {

	@Autowired
	NotificationService notificationService;
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	//View Notification By Employee Id
	@GetMapping("viewById")
	public ResponseEntity<List<Notification>> employeeNotifications(@RequestParam("empId") Long empId){
		List<Notification> notList;
		try {
			notList=notificationService.searchByEmpId(empId);
			for(Notification not: notList) {
				not.getToEmp().setReminders(null);
				not.getToEmp().setNotifications(null);
				not.getToEmp().setMeetings(null);
			}
			return new ResponseEntity<List<Notification>>(notList, HttpStatus.OK);
		} catch (NotificationException e) {
			return new ResponseEntity("No Notifications Found.", HttpStatus.BAD_REQUEST);
		}
	}
	
	//View Notification by Id
	@GetMapping("viewByNotId")
	public ResponseEntity<Notification> viewByNotId(@RequestParam("notId") Long notId){
		try {
			return new ResponseEntity<Notification>(notificationService.searchByNotId(notId), HttpStatus.OK);
		} catch (NotificationException e) {
			return new ResponseEntity("No Notifications Found.", HttpStatus.BAD_REQUEST);
		}
	}
	
	//View Unseen Notifications
	@GetMapping("viewUnseen")
	public ResponseEntity<List<Notification>> viewUnseen(@RequestParam("empId") Long empId){
		List<Notification> notList;
		try {
			notList=notificationService.viewUnseen(empId);
			for(Notification not: notList) {
				not.getToEmp().setReminders(null);
				not.getToEmp().setNotifications(null);
				not.getToEmp().setMeetings(null);
			}
			return new ResponseEntity<List<Notification>>(notList, HttpStatus.OK);
		} catch (NotificationException e) {
			return new ResponseEntity("No Notifications Found.", HttpStatus.BAD_REQUEST);
		}
	}
	
	//Set Notification as seen
	@GetMapping("setSeen")
	public ResponseEntity<Boolean> setAsSeen(@RequestParam("notId")Long notId) {
		try {
			return new ResponseEntity<Boolean>(notificationService.setAsSeen(notId), HttpStatus.OK);
		} catch (NotificationException e) {
			return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
		}
	}
	
	//Get notification count
	@GetMapping("getCount")
	public ResponseEntity<Integer> viewCount(@RequestParam("empId")Long empId){
		try {
			return new ResponseEntity<Integer>(notificationService.notificationCount(empId), HttpStatus.OK);
		} catch (NotificationException e) {
			return new ResponseEntity<Integer>(-1, HttpStatus.OK);
		}
	}
}
