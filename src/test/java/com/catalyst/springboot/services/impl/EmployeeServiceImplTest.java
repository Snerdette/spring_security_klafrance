package com.catalyst.springboot.services.impl;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.springboot.daos.EmployeeDao;
import com.catalyst.springboot.entities.Employee;
import com.catalyst.springboot.services.InvalidInputException;

public class EmployeeServiceImplTest {

	private EmployeeServiceImpl target;
	private EmployeeDao mockEmployeeDao;
	
	@Before
	public void setup(){
		target = new EmployeeServiceImpl();
		mockEmployeeDao = mock(EmployeeDao.class);
		target.setEmployeeDao(mockEmployeeDao);
	}

	@Test
	public void testAddEmployee(){
		target.add(null);
		verify(mockEmployeeDao, times(1)).add(null);
	}
	
	@Test
	public void testUpdateEmployee(){
		target.update(null);
		verify(mockEmployeeDao, times(1)).update(null);
	}
	
	@Test
	public void testDeleteEmployee(){
		
		target.delete(null);
		verify(mockEmployeeDao, times(1)).delete(null);
	}
	@Test(expected=InvalidInputException.class)
	public void testGetByEmployeeIdNull() throws InvalidInputException {
		target.getByEmployeeId(null);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testGetByEmployeeIdZero() throws InvalidInputException{
		target.getByEmployeeId(-1);
	}
	
	@Test
	public void testGetByEmployeeIdSuccess() throws InvalidInputException{
		target.getByEmployeeId(1);
		
		verify(mockEmployeeDao, times(1)).getByEmployeeId(any());
	}
	
	@Test
	public void testGetEmployeesNoFilter(){
		int expected = 2;
		List<Employee> employees = employeeList();
		when(mockEmployeeDao.getAllEmployees()).thenReturn(employees);
		List<Employee> actual = target.getEmployees(null);
		assertEquals(expected, actual.size());
		
	}

	@Test
	public void testGetEmployeesActive(){
		int expected = 1;
		List<Employee> employees = employeeList();
		when(mockEmployeeDao.getAllEmployees()).thenReturn(employees);
		List<Employee> actual = target.getEmployees(true);
		assertEquals(expected, actual.size());
	}
	
	@Test
	public void testGetEmployeesInactive(){
		int expected = 1;
		List<Employee> employees = employeeList();
		when(mockEmployeeDao.getAllEmployees()).thenReturn(employees);
		List<Employee> actual = target.getEmployees(false);
		assertEquals(expected, actual.size());
	}
	
	private List<Employee> employeeList() {
		List<Employee> employees = new ArrayList<>();
		Employee e = new Employee();
		e.setActive(true);
		employees.add(e);
		e = new Employee();
		e.setActive(false);
		employees.add(e);
		return employees;
	}
	
	
}
