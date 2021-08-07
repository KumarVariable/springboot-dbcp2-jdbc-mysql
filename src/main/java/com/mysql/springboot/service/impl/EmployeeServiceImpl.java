package com.mysql.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.springboot.dao.EmployeeDAO;
import com.mysql.springboot.model.Employee;
import com.mysql.springboot.service.EmployeeService;

/**
 * Implementation of business APIs EmployeeService
 * 
 * @author metanoia
 * @version 1.0
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO employeeDAO;

	@Override
	public int findMaxEmployeeNoFromEmployees() {
		return employeeDAO.findMaxEmployeeNoFromEmployees();
	}

	@Override
	public List<Employee> findAllEmployee() {
		return employeeDAO.findAllEmployee();
	}

	@Override
	public Employee findByEmployeeNumber(int employeeNumber) {
		return employeeDAO.findByEmployeeNumber(employeeNumber);
	}

	@Override
	public int insertEmployee(Employee employee) {
		return employeeDAO.insertEmployee(employee);
	}

	@Override
	public int updateEmployee(Employee employee, int employeeNumber) {
		return employeeDAO.updateEmployee(employee, employeeNumber);
	}

	@Override
	public int deleteEmployee(int employeeNumber) {
		return employeeDAO.deleteEmployee(employeeNumber);
	}

}
