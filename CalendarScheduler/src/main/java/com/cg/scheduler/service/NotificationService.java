/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import com.cg.scheduler.dto.Notification;
import com.cg.scheduler.exception.NotificationException;

/**
 * @author Devang created: 04/11/2019
 *
 */
public interface NotificationService {

	public Notification add(Notification notification) throws NotificationException;

	public List<Notification> read() throws NotificationException;

	public Notification update(Notification notification) throws NotificationException;

	public boolean delete(Long notId) throws NotificationException;

	public List<Notification> searchByEmpId(Long empId) throws NotificationException;
	
	public Notification searchByNotId(Long notId) throws NotificationException;

	public List<Notification> viewUnseen(Long empId) throws NotificationException;
	
	public boolean setAsSeen(Long notId) throws NotificationException;

}
