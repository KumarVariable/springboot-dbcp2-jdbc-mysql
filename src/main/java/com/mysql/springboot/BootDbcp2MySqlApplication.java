package com.mysql.springboot;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.mysql.springboot.model.Employee;
import com.mysql.springboot.service.EmployeeService;

/**
 * Boot main class to configure application.resolve dependencies.
 * 
 * @author metanoia
 * @version 1.0
 * 
 */
@SpringBootApplication(scanBasePackages = {"com.mysql.springboot"})
public class BootDbcp2MySqlApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BootDbcp2MySqlApplication.class);

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(BootDbcp2MySqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		int dummyEmployeeNumber = 10007;

		LOGGER.info(
				"Bean Count =  " + applicationContext.getBeanDefinitionCount());

		Map<String, DataSource> dataSourceMap = applicationContext
				.getBeansOfType(DataSource.class);

		for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
			LOGGER.info("Key = " + entry.getKey() + " , " + "Value = "
					+ entry.getValue());
		}

		LOGGER.info(
				" <<< --Read All employees from Employees Table, LIMIT to 10 records >>> ");

		List<Employee> employeeList = employeeService.findAllEmployee();

		LOGGER.info(
				String.format("Size of Employee List %d", employeeList.size()));

		LOGGER.info(" <<< --Read Employee, By Employee Number >>> "
				+ dummyEmployeeNumber);

		Employee employee = employeeService
				.findByEmployeeNumber(dummyEmployeeNumber);

		LOGGER.info("Employee Details -->> " + employee.toString());

		int maxEmployeeNumber = employeeService
				.findMaxEmployeeNoFromEmployees();

		LOGGER.info(" Max Employee Number ==  " + maxEmployeeNumber);

		LOGGER.info(" <<< --Uncomment, To Insert one new Employee >>> ");

		// Employee insertEmployee = getDummyInsert(maxEmployeeNumber);
		// int rowInserted = employeeService.insertEmployee(insertEmployee);
		// LOGGER.info(rowInserted + " record inserted successfully ");

		LOGGER.info(" <<< --Uncomment, To Update Employee Information >>> ");

		// Employee updateEmployee = getDummyUpdate(dummyEmployeeNumber);
		// int rowUpdated = employeeService.updateEmployee(updateEmployee,
		// dummyEmployeeNumber);
		// LOGGER.info(rowUpdated + " record updated successfully ");

		LOGGER.info(" <<< --Uncomment, To Delete Employee >>> ");
		// int recordDeleted = employeeService.deleteEmployee(maxEmployeeNumber);
		// LOGGER.info(recordDeleted + " record deleted successfully ");

	}
	
	/**
	 *  Private helper method to get Dummy data for Insert Operation.
	 */
	private Employee getDummyInsert(int maxEmployeeNumber) {
		Employee employee = new Employee();

		int empNumber = maxEmployeeNumber + 1;

		employee.setEmployeeNumber(empNumber);
		employee.setBirthDate("1983-09-11");
		employee.setFirstName("Kunal");
		employee.setLastName("Kumar");
		employee.setGender("M");
		employee.setHireDate("2010-03-10");

		return employee;
	}

	/**
	 *  Private helper method to get Dummy data for Update Operation.
	 */
	private Employee getDummyUpdate(int dummyEmployeeNumber) {
		Employee employee = new Employee();

		employee.setEmployeeNumber(dummyEmployeeNumber);
		employee.setBirthDate("1957-05-23");
		employee.setFirstName("Tzvetan Tze");
		employee.setLastName("Zielinski");
		employee.setGender("F");
		employee.setHireDate("1989-03-11");

		return employee;
	}

}
