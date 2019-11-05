/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import com.cg.scheduler.dto.Meeting;
import com.cg.scheduler.exception.MeetingException;

/**
 * @author Devang created: 04/11/2019
 *
 */
public interface MeetingService {

	public Meeting addMeeting(Meeting meeting) throws MeetingException;

	public List<Meeting> read() throws MeetingException;

	public Meeting update(Meeting meeting) throws MeetingException;

	public boolean cancel(Long meetingId) throws MeetingException;

	public Meeting approve(Long empId, Long meetingId) throws MeetingException;

	public Meeting maybe(Long empId, Long meetingId) throws MeetingException;

	public Meeting cancel(Long empId, Long meetingId) throws MeetingException;

}
