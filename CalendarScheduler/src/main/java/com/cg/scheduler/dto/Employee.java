/**
 * 
 */
package com.cg.scheduler.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author Devang created: 04/11/2019
 *
 */

@Entity(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long empId;
	private String empName;
	private String userName;
	private String empPassword;
	private String empPhone;
	private String empEmail;
	@OneToMany(mappedBy = "toEmp", fetch = FetchType.LAZY)
	private List<Notification> notifications;
	@OneToMany(mappedBy = "emp", fetch = FetchType.LAZY)
	private List<Reminder> reminders;
	@OneToMany(mappedBy = "organiser", fetch = FetchType.LAZY)
	private List<Meeting> meetings;
	private boolean active;
	private String roles;

	public Employee() {
		super();
	}

	public Employee(Long empId, String empName, String userName, String empPassword, String empPhone, String empEmail,
			List<Notification> notifications, List<Reminder> reminders, List<Meeting> meetings, boolean active,
			String roles) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.userName = userName;
		this.empPassword = empPassword;
		this.empPhone = empPhone;
		this.empEmail = empEmail;
		this.notifications = notifications;
		this.reminders = reminders;
		this.meetings = meetings;
		this.active = active;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", userName=" + userName + ", empPassword="
				+ empPassword + ", empPhone=" + empPhone + ", empEmail=" + empEmail + ", notifications=" + notifications
				+ ", reminders=" + reminders + ", meetings=" + meetings + ", active=" + active + ", roles=" + roles
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((empEmail == null) ? 0 : empEmail.hashCode());
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
		result = prime * result + ((empName == null) ? 0 : empName.hashCode());
		result = prime * result + ((empPassword == null) ? 0 : empPassword.hashCode());
		result = prime * result + ((empPhone == null) ? 0 : empPhone.hashCode());
		result = prime * result + ((meetings == null) ? 0 : meetings.hashCode());
		result = prime * result + ((notifications == null) ? 0 : notifications.hashCode());
		result = prime * result + ((reminders == null) ? 0 : reminders.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (active != other.active)
			return false;
		if (empEmail == null) {
			if (other.empEmail != null)
				return false;
		} else if (!empEmail.equals(other.empEmail))
			return false;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		if (empName == null) {
			if (other.empName != null)
				return false;
		} else if (!empName.equals(other.empName))
			return false;
		if (empPassword == null) {
			if (other.empPassword != null)
				return false;
		} else if (!empPassword.equals(other.empPassword))
			return false;
		if (empPhone == null) {
			if (other.empPhone != null)
				return false;
		} else if (!empPhone.equals(other.empPhone))
			return false;
		if (meetings == null) {
			if (other.meetings != null)
				return false;
		} else if (!meetings.equals(other.meetings))
			return false;
		if (notifications == null) {
			if (other.notifications != null)
				return false;
		} else if (!notifications.equals(other.notifications))
			return false;
		if (reminders == null) {
			if (other.reminders != null)
				return false;
		} else if (!reminders.equals(other.reminders))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public List<Reminder> getReminders() {
		return reminders;
	}

	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}

	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
