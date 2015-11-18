package com.catalyst.springboot.daos.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.catalyst.springboot.daos.EmployeeDao;
import com.catalyst.springboot.entities.Employee;


@Transactional
@Component 
public class EmployeeDaoHibernate implements EmployeeDao{

	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void add(Employee employee) {
		em.persist(employee);
		
	}


	@Override
	public List<Employee> getAllEmployees() {
		
		return em.
				createQuery("SELECT e FROM Employee e", Employee.class).
				getResultList();
	}

	@Override
	public Employee getByEmployeeId(Integer employeeId) {	
		return em
				.createQuery("SELECT e FROM Employee e WHERE e.employeeId = :id", Employee.class)
				.setParameter("id", employeeId)
				.getSingleResult();	 
	}

	@Override
	public Employee getEmployeeByUsername(String username){
		return em.createQuery("SELECT e FROM Employee e WHERE e.username = :username", Employee.class)
				 .setParameter("username", username)
				 .getSingleResult();
	}
	@Override
	public void update(Employee employee) { 
		em.merge(employee);
	}

	@Override
	public void delete(Integer employeeId) {
		Employee employee = getByEmployeeId(employeeId);
		em.remove(employee);
		
	}

}
