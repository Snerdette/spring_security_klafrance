package com.catalyst.springboot.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.catalyst.springboot.daos.EmployeeDao;
import com.catalyst.springboot.entities.Employee;
import com.catalyst.springboot.services.EmployeeService;
import com.catalyst.springboot.services.InvalidInputException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired(required = false)
	private BCryptPasswordEncoder encoder;

	public void setEncoder(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public List<Employee> getEmployees(Boolean active) {

		List<Employee> employees = employeeDao.getAllEmployees();
		if (active != null) {
			List<Employee> activeEmployees = new ArrayList<>();
			for (Employee e : employees) {
				if (e.isActive().equals(active)) {
					activeEmployees.add(e);
				}
			}
			return activeEmployees;
		}

		return employees;
	}

	@Override
	public void add(Employee employee) {

		String encryptedPass = encoder.encode(employee.getPassword());
		employee.setPassword(encryptedPass);
		employeeDao.add(employee);
	}

	@Override
	public void update(Employee employee) {
		String oldPassword = employeeDao.getByEmployeeId(employee.getEmployeeId()).getPassword();
		String newPassword = employee.getPassword();

		if (StringUtils.isEmpty(newPassword) || newPassword.equals(oldPassword)) {
			employee.setPassword(oldPassword);
		} else if (!encoder.matches(newPassword, oldPassword)) {
			employee.setPassword(encoder.encode(newPassword));
		} else {
			employee.setPassword(oldPassword);
		}
		employeeDao.update(employee);
	}

	@Override
	public Employee getByEmployeeId(Integer employeeId) throws InvalidInputException {
		if (employeeId == null || employeeId < 0) {
			throw new InvalidInputException("EmployeeId.NullOrNegative");
		}
		return employeeDao.getByEmployeeId(employeeId);
	}

	@Override
	public void delete(Integer employeeId) {
		employeeDao.delete(employeeId);

	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		return employeeDao.getEmployeeByUsername(username);

	}

}
