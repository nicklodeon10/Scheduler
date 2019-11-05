/**
 * 
 */
package com.cg.scheduler.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Devang created: 04/11/2019
 *
 */

@Entity(name = "Notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long notId;
	@ManyToOne
	private Employee toEmp;
	private String fromEmpId;
	private String notMessage;
	private LocalDateTime notTime;
	private boolean seen;

	public Notification() {
		super();
	}

	public Notification(Long notId, Employee toEmp, String fromEmp, String notMessage, LocalDateTime notTime,
			boolean seen) {
		super();
		this.notId = notId;
		this.toEmp = toEmp;
		this.fromEmpId = fromEmp;
		this.notMessage = notMessage;
		this.notTime = notTime;
		this.seen = seen;
	}

	@Override
	public String toString() {
		return "Notification [notId=" + notId + ", toEmp=" + toEmp + ", fromEmp=" + fromEmpId + ", notMessage="
				+ notMessage + ", notTime=" + notTime + ", seen=" + seen + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fromEmpId == null) ? 0 : fromEmpId.hashCode());
		result = prime * result + ((notId == null) ? 0 : notId.hashCode());
		result = prime * result + ((notMessage == null) ? 0 : notMessage.hashCode());
		result = prime * result + ((notTime == null) ? 0 : notTime.hashCode());
		result = prime * result + (seen ? 1231 : 1237);
		result = prime * result + ((toEmp == null) ? 0 : toEmp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Notification other = (Notification) obj;
		if (fromEmpId == null) {
			if (other.fromEmpId != null) {
				return false;
			}
		} else if (!fromEmpId.equals(other.fromEmpId)) {
			return false;
		}
		if (notId == null) {
			if (other.notId != null) {
				return false;
			}
		} else if (!notId.equals(other.notId)) {
			return false;
		}
		if (notMessage == null) {
			if (other.notMessage != null) {
				return false;
			}
		} else if (!notMessage.equals(other.notMessage)) {
			return false;
		}
		if (notTime == null) {
			if (other.notTime != null) {
				return false;
			}
		} else if (!notTime.equals(other.notTime)) {
			return false;
		}
		if (seen != other.seen) {
			return false;
		}
		if (toEmp == null) {
			if (other.toEmp != null) {
				return false;
			}
		} else if (!toEmp.equals(other.toEmp)) {
			return false;
		}
		return true;
	}

	public Long getNotId() {
		return notId;
	}

	public void setNotId(Long notId) {
		this.notId = notId;
	}

	public Employee getToEmp() {
		return toEmp;
	}

	public void setToEmp(Employee toEmp) {
		this.toEmp = toEmp;
	}

	public String getFromEmp() {
		return fromEmpId;
	}

	public void setFromEmp(String fromEmp) {
		this.fromEmpId = fromEmp;
	}

	public String getNotMessage() {
		return notMessage;
	}

	public void setNotMessage(String notMessage) {
		this.notMessage = notMessage;
	}

	public LocalDateTime getNotTime() {
		return notTime;
	}

	public void setNotTime(LocalDateTime notTime) {
		this.notTime = notTime;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

}