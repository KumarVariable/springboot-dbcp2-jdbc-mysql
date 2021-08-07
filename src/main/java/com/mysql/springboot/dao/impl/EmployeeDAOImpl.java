package com.mysql.springboot.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.mysql.springboot.dao.EmployeeDAO;
import com.mysql.springboot.model.Employee;
import com.mysql.springboot.resultset.mapper.EmployeeMpper;

/**
 * Implementation of persistence layer EmployeeDAO
 * 
 * @author metanoia
 * @version 1.0
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	NamedParameterJdbcTemplate namedJdbcTemplate;

	private static final String FIND_ALL_EMPLOYEES = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date "
			+ " FROM employees ORDER BY first_name LIMIT 10 ";

	private static final String FIND_BY_EMPLOYEE_NUMBER = "SELECT emp_no, birth_date, first_name, last_name, gender, hire_date "
			+ " FROM employees WHERE emp_no = :employeeNumber ";

	private static final String INSERT_INTO_EMPLOYEE = "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) "
			+ " VALUES (:employeeNumber, :birthDate, :firstName, :lastName, :gender, :hireDate ) ";

	private static final String UPDATE_INTO_EMPLOYEE = " UPDATE employees SET emp_no = :employeeNumber, birth_date = :birthDate, first_name = :firstName, "
			+ " last_name = :lastName, gender = :gender, hire_date = :hireDate WHERE emp_no = :employeeNumber ";

	private static final String DELETE_FROM_EMPLOYEE = "DELETE FROM employees WHERE emp_no = :employeeNumber ";

	/**
	 * 
	 */
	private static final String SELECT_MAX_EMP_NO_FROM_EMPLOYEES = "SELECT MAX(emp_no) from employees ";

	@Override
	public int findMaxEmployeeNoFromEmployees() {
		return namedJdbcTemplate.queryForObject(
				SELECT_MAX_EMP_NO_FROM_EMPLOYEES, new HashMap<String, Object>(),
				Integer.class);
	}

	@Override
	public List<Employee> findAllEmployee() {
		List<Employee> employeeList = namedJdbcTemplate
				.query(FIND_ALL_EMPLOYEES, new EmployeeMpper());

		if (employeeList == null || employeeList.size() == 0) {
			employeeList = new ArrayList<Employee>();
		}

		return employeeList;
	}

	@Override
	public Employee findByEmployeeNumber(int employeeNumber) {
		SqlParameterSource parameterSource = new MapSqlParameterSource(
				"employeeNumber", employeeNumber);
		Employee employee = namedJdbcTemplate.queryForObject(
				FIND_BY_EMPLOYEE_NUMBER, parameterSource, new EmployeeMpper());

		if (ObjectUtils.isEmpty(employee)) {
			employee = new Employee();
		}

		return employee;
	}

	@Override
	public int insertEmployee(Employee employee) {
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
				employee);
		int status = namedJdbcTemplate.update(INSERT_INTO_EMPLOYEE,
				namedParameters);
		return status;
	}

	@Override
	public int updateEmployee(Employee employee, int employeeNumber) {
		MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();

		sqlParameterSource.addValue("employeeNumber",
				employee.getEmployeeNumber());
		sqlParameterSource.addValue("birthDate", employee.getBirthDate());
		sqlParameterSource.addValue("firstName", employee.getFirstName());
		sqlParameterSource.addValue("lastName", employee.getLastName());
		sqlParameterSource.addValue("gender", employee.getGender());
		sqlParameterSource.addValue("hireDate", employee.getHireDate());
		sqlParameterSource.addValue("employeeNumber", employeeNumber);

		int status = namedJdbcTemplate.update(UPDATE_INTO_EMPLOYEE,
				sqlParameterSource);

		return status;
	}

	@Override
	public int deleteEmployee(int employeeNumber) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("employeeNumber", employeeNumber);

		int status = namedJdbcTemplate.update(DELETE_FROM_EMPLOYEE, paramMap);
		return status;
	}

}
