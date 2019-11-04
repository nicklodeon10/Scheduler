/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import com.cg.scheduler.dto.Reminder;

/**
 * @author Devang
 * created: 04/11/2019
 *
 */
public interface ReminderService {

	public Reminder create(Reminder reminder);
	
	public List<Reminder> read();
	
	public Reminder update(Reminder reminder);
	
	public boolean cancel(Long remId);
	
	public List<Reminder> searchByEmpId(Long empId);
	
	public List<Reminder> viewUpcoming(Long empId);
	
}
