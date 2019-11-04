/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.scheduler.dto.Notification;
import com.cg.scheduler.repository.EmployeeRepository;
import com.cg.scheduler.repository.NotificationRepository;

/**
 * @author Devang
 * created: 04/11/2019
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
	public Notification add(Notification notification) {
		return notificationRepository.save(notification);
	}

	@Override
	public List<Notification> read() {
		return notificationRepository.findAll();
	}

	@Override
	public Notification update(Notification notification) {
		return notificationRepository.save(notification);
	}

	@Override
	public boolean delete(Long notId) {
		notificationRepository.deleteById(notId);
		return true;
	}

	@Override
	public List<Notification> searchByEmpId(Long empId) {
		return notificationRepository.findByToEmp(employeeRepository.findById(empId).get());
	}

	@Override
	public List<Notification> viewUnseen(Long empId) {
		List<Notification> notificationList=searchByEmpId(empId);
		for(Notification notification: notificationList) {
			if(notification.isSeen()) {
				notificationList.remove(notification);
			}
		}
		return notificationList;
	}

}
