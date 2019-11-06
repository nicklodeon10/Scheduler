/**
 * 
 */
package com.cg.scheduler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.scheduler.dto.Employee;
import com.cg.scheduler.dto.Meeting;

/**
 * @author Devang created: 04/11/2019
 *
 */
@Repository("meetingRepository")
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

	List<Meeting> findByOrganiser(Employee organiser);
	
}
