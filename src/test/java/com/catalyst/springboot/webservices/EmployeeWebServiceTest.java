package com.catalyst.springboot.webservices;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.springboot.services.EmployeeService;

public class EmployeeWebServiceTest {
	private EmployeeService mockEmployeeService;
	private EmployeeWebService employeeWebService;

	@Before
	public void createEmployeeWebService() throws Exception {
		mockEmployeeService = mock(EmployeeService.class);
		employeeWebService = new EmployeeWebService();
		employeeWebService.setEmployeeService(mockEmployeeService);
	}

	@Test
	public void testAddEmployee() throws Exception {
		employeeWebService.addEmployee(null);
		verify(mockEmployeeService, times(1)).add(null);
	}

	@Test
	public void testGetEmployees() throws Exception {

		employeeWebService.getEmployees(null);
		verify(mockEmployeeService, times(1)).getEmployees(null);
	}

	@Test
	public void testGetEmployeeByID() throws Exception {
		employeeWebService.getEmployeeByID(null);
		verify(mockEmployeeService, times(1)).getByEmployeeId(null);
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		employeeWebService.updateEmployee(null,null);
		verify(mockEmployeeService, times(1)).update(null);
	}

	@Test
	public void testRemoveEmployee() throws Exception {
		employeeWebService.removeEmployee(null);
		verify(mockEmployeeService, times(1)).delete(null);
	}

}
