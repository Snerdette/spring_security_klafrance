package com.catalyst.springboot.services;

import java.util.List;

import com.catalyst.springboot.entities.Employee;

public interface EmployeeService {

	/**
	 * Add a new employee to the application.
	 * 
	 * @param employee
	 */
	void add(Employee employee);

	/**
	 * Update the information in an existing employee. The employeeId is used to
	 * find the unique employee's information.
	 * 
	 * @param employee
	 */
	void update(Employee employee);

	/**
	 * Removes the information about an employee from the application if their
	 * employeeId matches.
	 * 
	 * @param employeeId
	 *            -
	 */
	void delete(Integer employeeId);

	/**
	 * Retrieve all employees from the application.
	 * 
	 * @param isActive
	 *            - optional parameter. If supplied, will only retrieve
	 *            employees with matching values. If null, all employees are
	 *            returned.
	 * @return
	 */
	List<Employee> getEmployees(Boolean isActive);

	/**
	 * Retrieve a single employee's information if that employee's employeeId
	 * matches the supplied employeeId
	 * 
	 * @param employeeId
	 * @return
	 * @throws InvalidInputException
	 *             - the value of employeeId provided should not be null or less
	 *             than 0
	 */
	Employee getByEmployeeId(Integer employeeId) throws InvalidInputException;

	/**
	 * Retrieve a single employee's information if that employee's username
	 * matches the supplied username.
	 * 
	 * @param username
	 * @return
	 */
	Employee getEmployeeByUsername(String username);

}
