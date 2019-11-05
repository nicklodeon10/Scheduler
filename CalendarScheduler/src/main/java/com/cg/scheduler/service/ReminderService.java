/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import com.cg.scheduler.dto.Reminder;
import com.cg.scheduler.exception.ReminderException;

/**
 * @author Devang created: 04/11/2019
 *
 */
public interface ReminderService {

	public Reminder create(Reminder reminder) throws ReminderException;

	public List<Reminder> read() throws ReminderException;

	public Reminder update(Reminder reminder) throws ReminderException;

	public boolean cancel(Long remId) throws ReminderException;

	public List<Reminder> searchByEmpId(Long empId) throws ReminderException;

	public List<Reminder> viewUpcoming(Long empId) throws ReminderException;

}
