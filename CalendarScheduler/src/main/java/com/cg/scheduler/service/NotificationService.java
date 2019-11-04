/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import com.cg.scheduler.dto.Notification;

/**
 * @author Admin
 *
 */
public interface NotificationService {

	public Notification add(Notification notification);
	
	public List<Notification> read();
	
	public Notification update(Notification notification);
	
	public boolean delete(Long notId);
	
	public List<Notification> searchByEmpId(Long empId);
	
	public List<Notification> viewUnseen(Long empId);
	
}
