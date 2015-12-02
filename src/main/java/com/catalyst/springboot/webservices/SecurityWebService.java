package com.catalyst.springboot.webservices;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.springboot.entities.Employee;
import com.catalyst.springboot.services.EmployeeService;

@RestController
public class SecurityWebService {

	@Autowired EmployeeService employeeService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping (value="/security/current", method=RequestMethod.GET)
	public Employee currentUser(Principal principal){
		return employeeService.getEmployeeByUsername(principal.getName());
	}
}
