/**
 * 
 */
package com.cg.scheduler.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.scheduler.dto.Meeting;
import com.cg.scheduler.dto.Notification;
import com.cg.scheduler.dto.Reminder;
import com.cg.scheduler.exception.EmployeeException;
import com.cg.scheduler.exception.MeetingException;
import com.cg.scheduler.exception.NotificationException;
import com.cg.scheduler.exception.ReminderException;
import com.cg.scheduler.repository.MeetingRepository;

/**
 * @author Devang created: 04/11/2019
 *
 */

@Service("meetingService")
@Transactional
public class MeetingServiceImpl implements MeetingService {
	
	private static final Logger logger = LoggerFactory.getLogger(MeetingServiceImpl.class);

	@Autowired
	MeetingRepository meetingRepository;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	ReminderService reminderService;

	@Override
	public Meeting addMeeting(Meeting meeting) throws MeetingException {
		Meeting addedMeeting;
		try {
			logger.info("Saving");
			addedMeeting = meetingRepository.save(meeting);
		} catch (Exception exception) {
			logger.error("Error Adding Meeting.");
			throw new MeetingException("Error Adding Meeting.");
		}
		// Notify all Participants
		String message = addedMeeting.getMeetingTitle() + ", Organiser: " + addedMeeting.getOrganiser().getEmpName()
				+ ", Time: " + addedMeeting.getStartTime();
		String[] idTokens = addedMeeting.getParticipants().split(" ");
		Notification notification;
		for (String token : idTokens) {
			try {
				notification = new Notification();
				notification.setFromEmpId("" + addedMeeting.getOrganiser().getEmpId());
				notification.setNotMessage(message);
				notification.setNotTime(LocalDateTime.now());
				notification.setMeetingId(meeting.getMeetingId());
				notification.setSeen(false);
				notification.setToEmp(employeeService.searchById(Long.parseLong(token)));
			} catch (NumberFormatException e) {
				throw new MeetingException("Error in sending notifications. Could not parse Participant: " + token);
			} catch (EmployeeException e) {
				throw new MeetingException("Error in sending notifications. Could not find Employee: " + token);
			}
			try {
				notificationService.add(notification);
			} catch (NotificationException exception) {
				logger.error("Error in adding Notification.");
				throw new MeetingException("Error in adding Notification.");
			}
		}
		// Add reminders for organiser
		Reminder reminder = new Reminder();
		reminder.setDueTime(addedMeeting.getStartTime());
		reminder.setRemMessage(message);
		reminder.setActive(true);
		try {
			reminder.setEmp(employeeService.searchById(meeting.getOrganiser().getEmpId()));
		} catch (NumberFormatException e) {
			logger.error("Error in adding reminders. Could not parse Organiser.");
			throw new MeetingException("Error in adding reminders. Could not parse Organiser.");
		} catch (EmployeeException e) {
			logger.error("Error in adding reminders. Could not find Employee.");
			throw new MeetingException("Error in adding reminders. Could not find Employee.");
		}
		try {
			reminderService.create(reminder);
		} catch (ReminderException e) {
			logger.error("Error in adding reminder");
			throw new MeetingException("Error in adding reminder");
		}
		return addedMeeting;
	}

	@Override
	public List<Meeting> read() throws MeetingException {
		logger.info("Searching");
		List<Meeting> meetingList = meetingRepository.findAll();
		if (meetingList.size() == 0) {
			throw new MeetingException("No Meetings Found.");
		}
		return meetingList;
	}

	@Override
	public List<Meeting> viewOrganisedByEmployee(Long empId) throws MeetingException {
		List<Meeting> meetingList;
		try {
			logger.info("Searching");
			meetingList = meetingRepository.findByOrganiser(employeeService.searchById(empId));
		} catch (EmployeeException e) {
			throw new MeetingException("No Employee foudn for ID: " + empId);
		}
		if (meetingList.size() == 0) {
			throw new MeetingException("No Meetings Found.");
		}
		return meetingList;
	}

	@Override
	public Meeting update(Meeting meeting) throws MeetingException {
		Meeting updatedMeeting;
		try {
			logger.info("Searching");
			updatedMeeting = meetingRepository.save(meeting);
		} catch (Exception exception) {
			throw new MeetingException("Error Adding Meeting.");
		}
		return updatedMeeting;
	}

	@Override
	public boolean cancel(Long meetingId) throws MeetingException {
		Meeting meeting;
		try {
			logger.info("Searching");
			meeting = meetingRepository.findById(meetingId).get();
			meeting.setActive(false);
			meetingRepository.save(meeting);
		} catch (Exception exception) {
			throw new MeetingException("Error Cancelling Meeting.");
		}
		return true;
	}

	@Override
	public Meeting approve(Long empId, Long meetingId) throws MeetingException {
		logger.info("Approving");
		Meeting meeting = meetingRepository.findById(meetingId).get();
		String[] idTokens = meeting.getParticipants().split(" ");
		String[] statusTokens = meeting.getParticipantStatus().split(" ");
		String updatedStatus = "";
		for (int i = 0; i < idTokens.length; i++) {
			if (idTokens[i].equals("" + empId)) {
				statusTokens[i] = "approved";
			}
			updatedStatus += statusTokens[i] + " ";
		}
		meeting.setParticipantStatus(updatedStatus);
		// Notify Organiser
		String message;
		try {
			message ="MEETING UPDATE:" + meeting.getMeetingTitle() + ", Organiser: " + meeting.getOrganiser().getEmpName() + ", Time: "
					+ meeting.getStartTime() + ", Approved By: " + employeeService.searchById(empId).getEmpName();
		} catch (EmployeeException e) {
			throw new MeetingException("Could not find organiser.");
		}
		Notification notification = new Notification();
		try {
			notification.setFromEmpId("" + employeeService.searchById(empId).getEmpId());
		} catch (EmployeeException e) {
			throw new MeetingException("Could not find organiser.");
		}
		notification.setNotMessage(message);
		notification.setNotTime(LocalDateTime.now());
		notification.setMeetingId(meeting.getMeetingId());
		notification.setSeen(false);
		notification.setToEmp(meeting.getOrganiser());
		try {
			notificationService.add(notification);
		} catch (NotificationException exception) {
			throw new MeetingException("Error in adding Notification.");
		}
		// Add Reminder
		Reminder reminder = new Reminder();
		reminder.setDueTime(meeting.getStartTime());
		reminder.setRemMessage(message);
		reminder.setActive(true);
		try {
			reminder.setEmp(employeeService.searchById(empId));
		} catch (EmployeeException e) {
			throw new MeetingException("Could not find employee by id.");
		}
		return update(meeting);
	}

	@Override
	public Meeting maybe(Long empId, Long meetingId) throws MeetingException {
		logger.info("Maybe-ing");
		Meeting meeting = meetingRepository.findById(meetingId).get();
		String[] idTokens = meeting.getParticipants().split(" ");
		String[] statusTokens = meeting.getParticipantStatus().split(" ");
		String updatedStatus = "";
		for (int i = 0; i < idTokens.length; i++) {
			if (idTokens[i].equals("" + empId)) {
				statusTokens[i] = "maybe";
			}
			updatedStatus += statusTokens[i] + " ";
		}
		meeting.setParticipantStatus(updatedStatus);
		// Notify Organiser
		String message;
		try {
			message ="MEETING UPDATE:" + meeting.getMeetingTitle() + ", Organiser: " + meeting.getOrganiser().getEmpName() + ", Time: "
					+ meeting.getStartTime() + ", Maybe By: " + employeeService.searchById(empId).getEmpName();
		} catch (EmployeeException e) {
			throw new MeetingException("Could not find organiser.");
		}
		Notification notification = new Notification();
		try {
			notification.setFromEmpId("" + employeeService.searchById(empId).getEmpId());
		} catch (EmployeeException e) {
			throw new MeetingException("Could not find organiser.");
		}
		notification.setNotMessage(message);
		notification.setNotTime(LocalDateTime.now());
		notification.setMeetingId(meeting.getMeetingId());
		notification.setSeen(false);
		notification.setToEmp(meeting.getOrganiser());
		try {
			notificationService.add(notification);
		} catch (NotificationException exception) {
			throw new MeetingException("Error in adding Notification.");
		}
		return update(meeting);
	}

	@Override
	public Meeting cancel(Long empId, Long meetingId) throws MeetingException {
		logger.info("Cancelling");
		Meeting meeting = meetingRepository.findById(meetingId).get();
		String[] idTokens = meeting.getParticipants().split(" ");
		String[] statusTokens = meeting.getParticipantStatus().split(" ");
		String updatedStatus = "";
		for (int i = 0; i < idTokens.length; i++) {
			if (idTokens[i].equals("" + empId)) {
				statusTokens[i] = "cancelled";
			}
			updatedStatus += statusTokens[i] + " ";
		}
		meeting.setParticipantStatus(updatedStatus);
		// Notify Organiser
		String message;
		try {
			message ="MEETING UPDATE:" + meeting.getMeetingTitle() + ", Organiser: " + meeting.getOrganiser().getEmpName() + ", Time: "
					+ meeting.getStartTime() + ", Cancelled By: " + employeeService.searchById(empId).getEmpName();
		} catch (EmployeeException e) {
			throw new MeetingException("Could not find organiser.");
		}
		Notification notification = new Notification();
		try {
			notification.setFromEmpId("" + employeeService.searchById(empId).getEmpId());
		} catch (EmployeeException e) {
			throw new MeetingException("Could not find organiser.");
		}
		notification.setNotMessage(message);
		notification.setNotTime(LocalDateTime.now());
		notification.setMeetingId(meeting.getMeetingId());
		notification.setSeen(false);
		notification.setToEmp(meeting.getOrganiser());
		try {
			notificationService.add(notification);
		} catch (NotificationException exception) {
			throw new MeetingException("Error in adding Notification.");
		}
		return update(meeting);
	}

	@Override
	public List<Meeting> viewUpcoming(Long empId) throws MeetingException {
		logger.info("Searching");
		List<Meeting> meetingList=meetingRepository.findAll();
		List<Meeting> upcomingList=new ArrayList<>();
		for(Meeting meeting: meetingList) {
			if(meeting.getOrganiser().getEmpId()==empId) {
				if(meeting.getStartTime().isAfter(LocalDateTime.now())) {
					upcomingList.add(meeting);
				}
			}
			String[] tokens=meeting.getParticipants().split(" ");
			for(String token: tokens) {
				if(Long.parseLong(token)==empId) {
					if(meeting.getStartTime().isAfter(LocalDateTime.now())) {
						upcomingList.add(meeting);
					}
				}
			}
		}
		if(upcomingList.isEmpty()) {
			throw new MeetingException("No Meetings Found.");
		}
		return upcomingList;
	}

	@Override
	public List<Meeting> viewPastMeetings(Long empId) throws MeetingException {
		logger.info("Searching");
		List<Meeting> meetingList=meetingRepository.findAll();
		List<Meeting> pastList=new ArrayList<>();
		for(Meeting meeting: meetingList) {
			if(meeting.getOrganiser().getEmpId()==empId) {
				if(meeting.getStartTime().isBefore(LocalDateTime.now())) {
					pastList.add(meeting);
				}
			}
			String[] tokens=meeting.getParticipants().split(" ");
			for(String token: tokens) {
				if(Long.parseLong(token)==empId) {
					if(meeting.getStartTime().isBefore(LocalDateTime.now())) {
						pastList.add(meeting);
					}
				}
			}
		}
		if(pastList.isEmpty()) {
			throw new MeetingException("No Meetings Found.");
		}
		return pastList;
	}

	@Override
	public int upcomingMeetingsCount(Long empId) throws MeetingException {
		logger.info("Searching");
		List<Meeting> meetingList=viewUpcoming(empId);
		for(Meeting meeting: meetingList) {
			if(!meeting.isActive())
				meetingList.remove(meeting);
		}
		return meetingList.size();
	}

	@Override
	public Meeting getNext(Long empId) throws MeetingException {
		logger.info("Searching");
		List<Meeting> meetingList=viewUpcoming(empId);
		Meeting nextMeeting=meetingList.get(0);
		for(Meeting meeting:meetingList) {
			if(meeting.getStartTime().isBefore(nextMeeting.getStartTime())) {
				nextMeeting=meeting;
			}
		}
		return nextMeeting;
	}

}
