package com.mysql.springboot.dao;

import java.util.List;

import com.mysql.springboot.model.Employee;

/**
 * Abstract APIs to take part in database interaction for the 
 * user defined operations.
 * 
 * @author metanoia
 * @version 1.0
 */
public interface EmployeeDAO {

	public int findMaxEmployeeNoFromEmployees();

	public List<Employee> findAllEmployee();

	public Employee findByEmployeeNumber(int employeeNumber);

	public int insertEmployee(Employee employee);

	public int updateEmployee(Employee employee, int employeeNumber);

	public int deleteEmployee(int employeeNumber);

}
