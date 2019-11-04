/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.scheduler.dto.Employee;
import com.cg.scheduler.repository.EmployeeRepository;

/**
 * @author Devang
 * created: 04/11/2019
 *
 */

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee create(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> read() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee searchByName(String name) {
		return employeeRepository.findByEmpName(name).get(0);
	}

	@Override
	public Employee searchByEmail(String email) {
		return employeeRepository.findByEmpEmail(email).get(0);
	}

	@Override
	public Employee update(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public boolean delete(Long empId) {
		Employee emp=employeeRepository.findById(empId).get();
		emp.setActive(false);
		employeeRepository.save(emp);
		return true;
	}

}
