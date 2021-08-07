package com.mysql.springboot.service;

import java.util.List;

import com.mysql.springboot.model.Employee;

/**
 * Service APIs to handle business requirements.
 * 
 * @author metanoia
 * @version 1.0
 *
 */
public interface EmployeeService {

	public int findMaxEmployeeNoFromEmployees();

	public List<Employee> findAllEmployee();

	public Employee findByEmployeeNumber(int employeeNumber);

	public int insertEmployee(Employee employee);

	public int updateEmployee(Employee employee, int employeeNumber);

	public int deleteEmployee(int employeeNumber);

}
