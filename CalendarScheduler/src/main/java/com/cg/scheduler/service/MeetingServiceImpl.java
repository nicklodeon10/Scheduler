/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.scheduler.dto.Meeting;
import com.cg.scheduler.repository.MeetingRepository;

/**
 * @author Devang
 * created: 04/11/2019
 *
 */

@Service("meetingService")
@Transactional
public class MeetingServiceImpl implements MeetingService {
	
	@Autowired
	MeetingRepository meetingRepository;

	@Override
	public Meeting addMeeting(Meeting meeting) {
		return meetingRepository.save(meeting);
	}

	@Override
	public List<Meeting> read() {
		return meetingRepository.findAll();
	}

	@Override
	public Meeting update(Meeting meeting) {
		return meetingRepository.save(meeting);
	}

	@Override
	public boolean cancel(Long meetingId) {
		Meeting meeting=meetingRepository.findById(meetingId).get();
		meeting.setActive(false);
		return true;
	}

	@Override
	public Meeting approve(Long empId, Long meetingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting maybe(Long empId, Long meetingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting cancel(Long empId, Long meetingId) {
		// TODO Auto-generated method stub
		return null;
	}

}
