package com.catalyst.springboot.daos.hibernate;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.springboot.entities.Employee;

public class EmployeeDaoHibernateTest {

	private EmployeeDaoHibernate target;

	private EntityManager mockEm;

	@Before
	public void setup() {
		target = new EmployeeDaoHibernate();
		mockEm = mock(EntityManager.class);
		target.setEm(mockEm);
	}

	@Test
	public void testAdd() {
		
		target.add(null);
	
		//We have nothing we can assert. So use verify to check how many times a dependency's method was called.
		verify(mockEm, times(1)).persist(null);
	}

	@Test
	public void testGetAllEmployees() {

		List<Employee> expected = new ArrayList<>();

		TypedQuery<Employee> mockTypedQuery = mock(TypedQuery.class);

		when(mockEm.createQuery(anyString(), eq(Employee.class))).thenReturn(mockTypedQuery);
		when(mockTypedQuery.getResultList()).thenReturn(expected);

		target.getAllEmployees();

		verify(mockTypedQuery, times(1)).getResultList();

	}

	@Test
	public void testGetByEmployeeId() {
		TypedQuery<Employee> mockTypedQuery = mock(TypedQuery.class);

		when(mockEm.createQuery(anyString(), eq(Employee.class))).thenReturn(mockTypedQuery);
		when(mockTypedQuery.setParameter(anyString(), anyInt())).thenReturn(mockTypedQuery);
		 		 
		
		
		target.getByEmployeeId(0);
		verify(mockTypedQuery, times(1)).setParameter(eq("id"), eq(0));
		
	}

	@Test
	public void testUpdate() {
		Employee expected = new Employee();
		expected.setEmployeeId(1);
	
		target.update(expected);
		
		verify(mockEm, times(1)).merge(expected);
	}

	@Test
	public void testDelete() {
		Employee employeeToDelete = new Employee();
		employeeToDelete.setEmployeeId(5);
		
		TypedQuery<Employee> mockTypedQuery = mock(TypedQuery.class);

		when(mockEm.createQuery(anyString(), eq(Employee.class))).thenReturn(mockTypedQuery);
		when(mockTypedQuery.setParameter(anyString(), anyInt())).thenReturn(mockTypedQuery);
		when(mockTypedQuery.getSingleResult()).thenReturn(employeeToDelete);
		
		target.delete(5);
		
		verify(mockEm, times(1)).remove(employeeToDelete);
		verify(mockTypedQuery, times(1)).setParameter(eq("id"), eq(5));
		
	}
	
 

}
