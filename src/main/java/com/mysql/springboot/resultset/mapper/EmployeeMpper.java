package com.mysql.springboot.resultset.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.RowMapper;

import com.mysql.springboot.model.Employee;

/**
 * Row Mapper to map each row from result set.
 * 
 * @author metanoia
 * @version 1.0
 */

public class EmployeeMpper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet resultSet, int rowNum)
			throws SQLException {

		Employee employee = new Employee();

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

		employee.setEmployeeNumber(resultSet.getInt("emp_no"));;
		employee.setFirstName(resultSet.getString("first_name"));
		employee.setLastName(resultSet.getString("last_name"));
		employee.setGender(resultSet.getString("gender"));

		String hireDate = dateFormatter.format(resultSet.getDate("hire_date"));
		String birthDate = dateFormatter
				.format(resultSet.getDate("birth_date"));

		employee.setBirthDate(birthDate);
		employee.setHireDate(hireDate);

		return employee;
	}

}
