/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.scheduler.dto.Notification;
import com.cg.scheduler.exception.NotificationException;
import com.cg.scheduler.repository.EmployeeRepository;
import com.cg.scheduler.repository.NotificationRepository;

/**
 * @author Devang created: 04/11/2019
 *
 */

@Service("notificationService")
@Transactional
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Notification add(Notification notification) throws NotificationException {
		Notification addedNotification;
		try {
			addedNotification = notificationRepository.save(notification);
		} catch (Exception exception) {
			throw new NotificationException("Error Adding Notification.");
		}
		addedNotification.getToEmp().setNotifications(null);
		addedNotification.getToEmp().setReminders(null);
		addedNotification.getToEmp().setMeetings(null);
		return addedNotification;
	}

	@Override
	public List<Notification> read() throws NotificationException {
		List<Notification> notificationList = notificationRepository.findAll();
		if (notificationList.size() == 0) {
			throw new NotificationException("No Notifications Found.");
		}
		return notificationList;
	}

	@Override
	public Notification update(Notification notification) throws NotificationException {
		Notification updatedNotification;
		try {
			updatedNotification = notificationRepository.save(notification);
		} catch (Exception exception) {
			throw new NotificationException("Error Updating Notification.");
		}
		return updatedNotification;
	}

	@Override
	public boolean delete(Long notId) throws NotificationException {
		try {
			notificationRepository.deleteById(notId);
		} catch (Exception exception) {
			throw new NotificationException("Error Deleting Notification.");
		}
		return true;
	}

	@Override
	public List<Notification> searchByEmpId(Long empId) throws NotificationException {
		List<Notification> notificationList = notificationRepository
				.findByToEmp(employeeRepository.findById(empId).get());
		if (notificationList.size() == 0) {
			throw new NotificationException("No Notification Found.");
		}
		return notificationList;
	}

	@Override
	public Notification searchByNotId(Long notId) throws NotificationException {
		Notification notification = notificationRepository.findById(notId).get();
		if (notification == null) {
			throw new NotificationException("Notification not found.");
		}
		return notification;
	}

	@Override
	public List<Notification> viewUnseen(Long empId) throws NotificationException {
		List<Notification> notificationList = searchByEmpId(empId);
		for (Notification notification : notificationList) {
			if (notification.isSeen()) {
				notificationList.remove(notification);
			}
		}
		return notificationList;
	}

	@Override
	public boolean setAsSeen(Long notId) throws NotificationException {
		Notification notification = searchByNotId(notId);
		notification.setSeen(true);
		return true;
	}

}
