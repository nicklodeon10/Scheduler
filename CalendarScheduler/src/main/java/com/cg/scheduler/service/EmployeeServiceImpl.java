/**
 * 
 */
package com.cg.scheduler.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee create(Employee employee) throws EmployeeException {
		Employee emp;
		try {
			logger.info("Adding Employee");
			emp = employeeRepository.save(employee);
		} catch (Exception exception) {
			logger.error("Error Adding Employee.");
			throw new EmployeeException("Error Adding Employee.");
		}
		return emp;
	}

	@Override
	public List<Employee> read() throws EmployeeException {
		logger.info("Searching");
		List<Employee> empList = employeeRepository.findAll();
		if (empList.size() == 0) {
			logger.error("No Employee Found.");
			throw new EmployeeException("No Employee Found.");
		}
		return empList;
	}

	@Override
	public List<Employee> searchByName(String name) throws EmployeeException {
		logger.info("Searching");
		List<Employee> empList = employeeRepository.findByEmpName(name);
		if (empList.size()==0) {
			logger.error("No Employee Found.");
			throw new EmployeeException("Employee Not Found.");
		}
		return empList;
	}

	@Override
	public Employee searchByEmail(String email) throws EmployeeException {
		logger.info("Searching");
		Employee emp = employeeRepository.findByEmpEmail(email).get(0);
		if (emp == null) {
			logger.error("No Employee Found.");
			throw new EmployeeException("Employee Not Found.");
		}
		return emp;
	}

	@Override
	public Employee searchById(Long empId) throws EmployeeException {
		logger.info("Searching");
		Employee emp = employeeRepository.findById(empId).get();
		if (emp == null) {
			logger.error("No Employee Found.");
			throw new EmployeeException("Employee Not Found.");
		}
		return emp;
	}

	@Override
	public Employee update(Employee employee) throws EmployeeException {
		Employee emp;
		try {
			logger.info("Updating");
			emp = employeeRepository.save(employee);
		} catch (Exception exception) {
			logger.error("Error Updating Employee.");
			throw new EmployeeException("Error Updating Employee.");
		}
		return emp;
	}

	@Override
	public boolean delete(Long empId) throws EmployeeException {
		try {
			logger.info("Searching");
			Employee emp = employeeRepository.findById(empId).get();
			emp.setActive(false);
			employeeRepository.save(emp);
		} catch (Exception exception) {
			logger.error("Error Deleting Employee.");
			throw new EmployeeException("Error Deleting Employee.");
		}
		return true;
	}

	@Override
	public Employee searchByUsername(String username) throws EmployeeException {
		logger.info("Searching");
		return employeeRepository.findByUserName(username).get();
	}

}
