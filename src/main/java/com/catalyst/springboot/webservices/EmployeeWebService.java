package com.catalyst.springboot.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.springboot.entities.Employee;
import com.catalyst.springboot.services.EmployeeService;
import com.catalyst.springboot.services.InvalidInputException; 

@RestController
public class EmployeeWebService {

	@Autowired
	private EmployeeService employeeService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	
	
	@RequestMapping(value="/employees", method=RequestMethod.POST)
	public void addEmployee(@RequestBody Employee employee){
		employeeService.add(employee);
	} 
		
		
	@RequestMapping(value="/employees", method = RequestMethod.GET)
	public List<Employee> getEmployees(Boolean active){
		return employeeService.getEmployees(active);
	}	
	

	
	@RequestMapping(value="/employees/{id}", method=RequestMethod.GET)
	public Employee getEmployeeByID(@PathVariable Integer id) throws InvalidInputException{ 
		return employeeService.getByEmployeeId(id);
	}
	
	@RequestMapping(value="/employees/{id}", method = RequestMethod.PUT)
	public void updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
		employeeService.update(employee);
	}
	
	@RequestMapping(value="/employees/{id}", method = RequestMethod.DELETE)
	public void removeEmployee(@PathVariable Integer id){
		
		employeeService.delete(id);
		 
	}
	 
	
	
 
	
 
	
	
	
	
}
