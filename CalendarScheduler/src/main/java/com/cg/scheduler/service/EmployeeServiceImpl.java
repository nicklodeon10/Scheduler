/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.scheduler.dto.Employee;
import com.cg.scheduler.exception.EmployeeException;
import com.cg.scheduler.repository.EmployeeRepository;

/**
 * @author Devang created: 04/11/2019
 *
 */

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee create(Employee employee) throws EmployeeException {
		Employee emp;
		try {
			emp = employeeRepository.save(employee);
		} catch (Exception exception) {
			throw new EmployeeException("Error Adding Employee.");
		}
		return emp;
	}

	@Override
	public List<Employee> read() throws EmployeeException {
		List<Employee> empList = employeeRepository.findAll();
		if (empList.size() == 0) {
			throw new EmployeeException("No Employee Found.");
		}
		return empList;
	}

	@Override
	public Employee searchByName(String name) throws EmployeeException {
		Employee emp = employeeRepository.findByEmpName(name).get(0);
		if (emp == null) {
			throw new EmployeeException("Employee Not Found.");
		}
		return emp;
	}

	@Override
	public Employee searchByEmail(String email) throws EmployeeException {
		Employee emp = employeeRepository.findByEmpEmail(email).get(0);
		if (emp == null) {
			throw new EmployeeException("Employee Not Found.");
		}
		return emp;
	}

	@Override
	public Employee searchById(Long empId) throws EmployeeException {
		Employee emp = employeeRepository.findById(empId).get();
		if (emp == null) {
			throw new EmployeeException("Employee Not Found.");
		}
		return emp;
	}

	@Override
	public Employee update(Employee employee) throws EmployeeException {
		Employee emp;
		try {
			emp = employeeRepository.save(employee);
		} catch (Exception exception) {
			throw new EmployeeException("Error Updating Employee.");
		}
		return emp;
	}

	@Override
	public boolean delete(Long empId) throws EmployeeException {
		try {
			Employee emp = employeeRepository.findById(empId).get();
			emp.setActive(false);
			employeeRepository.save(emp);
		} catch (Exception exception) {
			throw new EmployeeException("Error Deleting Employee.");
		}
		return true;
	}

}
