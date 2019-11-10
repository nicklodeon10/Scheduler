/**
 * 
 */
package com.cg.scheduler.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.scheduler.dto.Notification;
import com.cg.scheduler.exception.NotificationException;
import com.cg.scheduler.service.NotificationService;

/**
 * @author nicklodeon10
 *
 */

@RestController
@RequestMapping("notification")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

	@Autowired
	NotificationService notificationService;

	@GetMapping("viewById")
	public ResponseEntity<List<Notification>> employeeNotifications(@RequestParam("empId") Long empId){
		try {
			return new ResponseEntity<List<Notification>>(notificationService.searchByEmpId(empId), HttpStatus.OK);
		} catch (NotificationException e) {
			return new ResponseEntity("No Notifications Found.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("viewByNotId")
	public ResponseEntity<Notification> viewByNotId(@RequestParam("notId") Long notId){
		try {
			return new ResponseEntity<Notification>(notificationService.searchByNotId(notId), HttpStatus.OK);
		} catch (NotificationException e) {
			return new ResponseEntity("No Notifications Found.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("viewUnseen")
	public ResponseEntity<List<Notification>> viewUnseen(@RequestParam("empId") Long empId){
		try {
			return new ResponseEntity<List<Notification>>(notificationService.viewUnseen(empId), HttpStatus.OK);
		} catch (NotificationException e) {
			return new ResponseEntity("No Notifications Found.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("setSeen")
	public ResponseEntity<Boolean> setAsSeen(@RequestParam("notId")Long notId) {
		try {
			return new ResponseEntity<Boolean>(notificationService.setAsSeen(notId), HttpStatus.OK);
		} catch (NotificationException e) {
			return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("getCount")
	public ResponseEntity<Integer> viewCount(@RequestParam("empId")Long empId){
		try {
			return new ResponseEntity<Integer>(notificationService.notificationCount(empId), HttpStatus.OK);
		} catch (NotificationException e) {
			return new ResponseEntity<Integer>(0, HttpStatus.OK);
		}
	}
}
