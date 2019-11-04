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
 * @author Devang
 * created: 04/11/2019
 *
 */

@Entity(name="Meeting")
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long meetingId;
	private String meetingTitle;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	@ManyToOne
	private Employee organiser;
	private String participants;
	private String participantStatus;
	private String location;
	private boolean active;
	
	public Meeting() {
		super();
	}

	public Meeting(Long meetingId, String meetingTitle, LocalDateTime startTime, LocalDateTime endTime,
			Employee organiser, String participants, String participantStatus, String location, boolean active) {
		super();
		this.meetingId = meetingId;
		this.meetingTitle = meetingTitle;
		this.startTime = startTime;
		this.endTime = endTime;
		this.organiser = organiser;
		this.participants = participants;
		this.participantStatus = participantStatus;
		this.location = location;
		this.active = active;
	}

	@Override
	public String toString() {
		return "Meeting [meetingId=" + meetingId + ", meetingTitle=" + meetingTitle + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", organiser=" + organiser + ", participants=" + participants
				+ ", participantStatus=" + participantStatus + ", location=" + location + ", active=" + active + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((meetingId == null) ? 0 : meetingId.hashCode());
		result = prime * result + ((meetingTitle == null) ? 0 : meetingTitle.hashCode());
		result = prime * result + ((organiser == null) ? 0 : organiser.hashCode());
		result = prime * result + ((participantStatus == null) ? 0 : participantStatus.hashCode());
		result = prime * result + ((participants == null) ? 0 : participants.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		Meeting other = (Meeting) obj;
		if (active != other.active) {
			return false;
		}
		if (endTime == null) {
			if (other.endTime != null) {
				return false;
			}
		} else if (!endTime.equals(other.endTime)) {
			return false;
		}
		if (location == null) {
			if (other.location != null) {
				return false;
			}
		} else if (!location.equals(other.location)) {
			return false;
		}
		if (meetingId == null) {
			if (other.meetingId != null) {
				return false;
			}
		} else if (!meetingId.equals(other.meetingId)) {
			return false;
		}
		if (meetingTitle == null) {
			if (other.meetingTitle != null) {
				return false;
			}
		} else if (!meetingTitle.equals(other.meetingTitle)) {
			return false;
		}
		if (organiser == null) {
			if (other.organiser != null) {
				return false;
			}
		} else if (!organiser.equals(other.organiser)) {
			return false;
		}
		if (participantStatus == null) {
			if (other.participantStatus != null) {
				return false;
			}
		} else if (!participantStatus.equals(other.participantStatus)) {
			return false;
		}
		if (participants == null) {
			if (other.participants != null) {
				return false;
			}
		} else if (!participants.equals(other.participants)) {
			return false;
		}
		if (startTime == null) {
			if (other.startTime != null) {
				return false;
			}
		} else if (!startTime.equals(other.startTime)) {
			return false;
		}
		return true;
	}

	public Long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Long meetingId) {
		this.meetingId = meetingId;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Employee getOrganiser() {
		return organiser;
	}

	public void setOrganiser(Employee organiser) {
		this.organiser = organiser;
	}

	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public String getParticipantStatus() {
		return participantStatus;
	}

	public void setParticipantStatus(String participantStatus) {
		this.participantStatus = participantStatus;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
