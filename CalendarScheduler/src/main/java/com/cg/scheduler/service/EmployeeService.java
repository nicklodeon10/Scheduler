/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import com.cg.scheduler.dto.Employee;
import com.cg.scheduler.exception.EmployeeException;

/**
 * @author Devang created: 04/11/2019
 *
 */
public interface EmployeeService {

	public Employee create(Employee employee) throws EmployeeException;

	public List<Employee> read() throws EmployeeException;

	public Employee searchByName(String name) throws EmployeeException;

	public Employee searchByEmail(String email) throws EmployeeException;
	
	public Employee searchById(Long empId) throws EmployeeException;

	public Employee update(Employee employee) throws EmployeeException;

	public boolean delete(Long empId) throws EmployeeException;

}
