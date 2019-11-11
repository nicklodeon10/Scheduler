/**
 * 
 */
package com.cg.scheduler.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.scheduler.dto.Employee;

/**
 * @author Devang created: 04/11/2019
 *
 */
@Repository("employeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> findByEmpName(String empName);

	public List<Employee> findByEmpEmail(String empEmail);
	
	public Optional<Employee> findByUserName(String userName);

}
