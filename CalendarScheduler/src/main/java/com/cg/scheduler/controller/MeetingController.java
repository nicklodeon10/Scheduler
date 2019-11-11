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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.scheduler.dto.Meeting;
import com.cg.scheduler.exception.EmployeeException;
import com.cg.scheduler.exception.MeetingException;
import com.cg.scheduler.service.EmployeeService;
import com.cg.scheduler.service.MeetingService;

/**
 * @author Devang created: 05/11/2019
 *
 */

@RestController
@RequestMapping("meeting")
@CrossOrigin(origins = "http://localhost:4200")
public class MeetingController {

	@Autowired
	MeetingService meetingService;

	@Autowired
	EmployeeService employeeService;

	@PostMapping("add")
	public ResponseEntity<Meeting> scheduleMeeting(@RequestBody Meeting meeting,
			@RequestParam("organiserId") Long organiserId) {
		Meeting newMeeting = null;
		String[] idTokens = meeting.getParticipants().split(" ");
		String participantStatus="";
		for(int i=0; i<idTokens.length; i++) {
			participantStatus+="nores ";
		}
		meeting.setParticipantStatus(participantStatus);
		try {
			meeting.setOrganiser(employeeService.searchById(organiserId));
			newMeeting = meetingService.addMeeting(meeting);
			newMeeting.getOrganiser().setNotifications(null);
			newMeeting.getOrganiser().setMeetings(null);
			newMeeting.getOrganiser().setReminders(null);
		} catch (EmployeeException exception) {
			return new ResponseEntity("Could not find employee by that id. Please try again.", HttpStatus.BAD_REQUEST);
		} catch (MeetingException e) {
			return new ResponseEntity("Unable to add the meeting. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Meeting>(newMeeting, HttpStatus.OK);
	}

	@GetMapping("view")
	public ResponseEntity<List<Meeting>> getMeetingsForEmployee(@RequestParam("empId") Long empId) {
		List<Meeting> meetingList;
		try {
			meetingList = meetingService.viewOrganisedByEmployee(empId);
			for (Meeting meeting : meetingList) {
				meeting.getOrganiser().setNotifications(null);
				meeting.getOrganiser().setMeetings(null);
				meeting.getOrganiser().setReminders(null);
			}
		} catch (MeetingException e) {
			return new ResponseEntity("Unable to find meetings. Please try again.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Meeting>>(meetingList, HttpStatus.OK);
	}

	@PostMapping("cancel")
	public ResponseEntity<String> cancelMeeting(@RequestParam("meetingId") Long meetingId) {
		try {
			meetingService.cancel(meetingId);
		} catch (MeetingException e) {
			return new ResponseEntity<String>("Unable to Cancel Meeting. Please try again.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Meeting Cancelled.", HttpStatus.OK);
	}

	@PostMapping("respond/approve")
	public ResponseEntity<Meeting> respondApproveMeeting(@RequestParam("empId") Long empId,
			@RequestParam("meetingId") Long meetingId) {
		Meeting meeting;
		try {
			meeting=meetingService.approve(empId, meetingId);
			meeting.getOrganiser().setNotifications(null);
			meeting.getOrganiser().setMeetings(null);
			meeting.getOrganiser().setReminders(null);
		} catch (MeetingException e) {
			return new ResponseEntity("Unable to Approve. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}
	
	@PostMapping("respond/maybe")
	public ResponseEntity<Meeting> respondMaybeMeeting(@RequestParam("empId") Long empId,
			@RequestParam("meetingId") Long meetingId) {
		Meeting meeting;
		try {
			meeting=meetingService.maybe(empId, meetingId);
			meeting.getOrganiser().setNotifications(null);
			meeting.getOrganiser().setMeetings(null);
			meeting.getOrganiser().setReminders(null);
		} catch (MeetingException e) {
			return new ResponseEntity("Unable to Approve. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}
	
	@PostMapping("respond/cancel")
	public ResponseEntity<Meeting> respondCancelMeeting(@RequestParam("empId") Long empId,
			@RequestParam("meetingId") Long meetingId) {
		Meeting meeting;
		try {
			meeting=meetingService.cancel(empId, meetingId);
			meeting.getOrganiser().setNotifications(null);
			meeting.getOrganiser().setMeetings(null);
			meeting.getOrganiser().setReminders(null);
		} catch (MeetingException e) {
			return new ResponseEntity("Unable to Approve. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}

	@GetMapping("upcoming/view")
	public ResponseEntity<List<Meeting>> viewUpcomingMeetings(@RequestParam("empId") Long empId){
		List<Meeting> meetingList;
		try {
			meetingList=meetingService.viewUpcoming(empId);
			for(Meeting meeting: meetingList) {
				meeting.getOrganiser().setMeetings(null);
				meeting.getOrganiser().setNotifications(null);
				meeting.getOrganiser().setReminders(null);
			}
		} catch (MeetingException e) {
			return new ResponseEntity("Could not retrieve any meetings. Please try again.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Meeting>>(meetingList, HttpStatus.OK);
	}
	
	@GetMapping("past/view")
	public ResponseEntity<List<Meeting>> viewPastMeetings(@RequestParam("empId") Long empId){
		List<Meeting> meetingList;
		try {
			meetingList=meetingService.viewPastMeetings(empId);
			for(Meeting meeting: meetingList) {
				meeting.getOrganiser().setMeetings(null);
				meeting.getOrganiser().setNotifications(null);
				meeting.getOrganiser().setReminders(null);
			}
		} catch (MeetingException e) {
			return new ResponseEntity("Could not retrieve any meetings. Please try again.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Meeting>>(meetingList, HttpStatus.OK);
	}
	
	@GetMapping("getCount")
	public ResponseEntity<Integer> getCount(@RequestParam("empId")Long empId){
		try {
			return new ResponseEntity<Integer>(meetingService.upcomingMeetingsCount(empId), HttpStatus.OK);
		} catch (MeetingException e) {
			return new ResponseEntity<Integer>(-1, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("getNext")
	public ResponseEntity<Meeting> getNext(@RequestParam("empId")Long empId){
		Meeting meeting;
		try {
			meeting=meetingService.getNext(empId);
			meeting.getOrganiser().setMeetings(null);
			meeting.getOrganiser().setNotifications(null);
			meeting.getOrganiser().setReminders(null);
			return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
		} catch (MeetingException e) {
			return new ResponseEntity("Could not retrieve any meetings. Please try again.", HttpStatus.BAD_REQUEST);
		}
	}
}
