/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import com.cg.scheduler.dto.Meeting;

/**
 * @author Devang
 * created: 04/11/2019
 *
 */
public interface MeetingService {

	public Meeting addMeeting(Meeting meeting);
	
	public List<Meeting> read();
	
	public Meeting update(Meeting meeting);
	
	public boolean cancel(Long meetingId);
	
	public Meeting approve(Long empId, Long meetingId);
	
	public Meeting maybe(Long empId, Long meetingId);
	
	public Meeting cancel(Long empId, Long meetingId);
	
}
