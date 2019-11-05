/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.scheduler.dto.Meeting;
import com.cg.scheduler.exception.MeetingException;
import com.cg.scheduler.repository.MeetingRepository;

/**
 * @author Devang created: 04/11/2019
 *
 */

@Service("meetingService")
@Transactional
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	MeetingRepository meetingRepository;

	@Override
	public Meeting addMeeting(Meeting meeting) throws MeetingException {
		Meeting addedMeeting;
		try {
			addedMeeting = meetingRepository.save(meeting);
		} catch (Exception exception) {
			throw new MeetingException("Error Adding Meeting.");
		}
		return addedMeeting;
	}

	@Override
	public List<Meeting> read() throws MeetingException {
		List<Meeting> meetingList = meetingRepository.findAll();
		if (meetingList.size() == 0) {
			throw new MeetingException("No Meetings Found.");
		}
		return meetingList;
	}

	@Override
	public Meeting update(Meeting meeting) throws MeetingException {
		Meeting updatedMeeting;
		try {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting maybe(Long empId, Long meetingId) throws MeetingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting cancel(Long empId, Long meetingId) throws MeetingException {
		// TODO Auto-generated method stub
		return null;
	}

}
