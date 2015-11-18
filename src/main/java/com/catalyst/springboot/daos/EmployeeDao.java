package com.catalyst.springboot.daos;

import java.util.List;

import com.catalyst.springboot.entities.Employee;

/**
 * The methods declared in this interface are to provide basic CRUD functionality related to Employee objects.
 * @author Ernest
 *
 */
public interface EmployeeDao {

	/**
	 * Adds information about an employee to storage.
	 * @param employee
	 */
	void add(Employee employee);

	/**
	 * Updates the information about an employee already in storage.
	 * @param employee
	 */
	void update(Employee employee);
	
	/**
	 * Retrieves all employees currently in storage.
	 * @return
	 */
	List<Employee> getAllEmployees();
	
	/**
	 * Retrieve information about a single employee from storage with a matching employeeId
	 * @param employeeId
	 * @return
	 */
	Employee getByEmployeeId(Integer employeeId);

	/**
	 * Deletes information about an employee with a matching employeeId
	 * @param employeeId
	 */
	void delete(Integer employeeId);

	/**
	 * Retrieves an employee with a matching username.
	 * 
	 * @param username
	 * @return
	 */
	Employee getEmployeeByUsername(String username);
	
	
	

}