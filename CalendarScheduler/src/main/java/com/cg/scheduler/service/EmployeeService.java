/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import com.cg.scheduler.dto.Employee;

/**
 * @author Devang
 * created: 04/11/2019
 *
 */
public interface EmployeeService {
	
	public Employee create(Employee employee);
	
	public List<Employee> read();
	
	public Employee searchByName(String name);
	
	public Employee searchByEmail(String email);
	
	public Employee update(Employee employee);
	
	public boolean delete(Long empId);

}
