package com.rahavoi.sanbox.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.rahavoi.entity.Employee;

public class EmployeeService {
	private EntityManager em;
	
	public EmployeeService(EntityManager em){
		this.em = em;
	}
	
	public Employee createEmployee(String name, Long salary){
		Employee emp = new Employee();
		emp.setName(name);
		emp.setSalary(salary);
		
		em.persist(emp);
		
		return emp;
	}
	
	public void removeEmployee(int id){
		Employee emp = findEmployee(id);
		if(emp != null){
			em.remove(emp);
		}
	}
	
	public Employee findEmployee(int id){
		return em.find(Employee.class, id);
	}
	
	public Employee raiseEmployeeSalary(int id, long raise){
		Employee emp = em.find(Employee.class, id);
		
		if(emp != null){
			emp.setSalary(emp.getSalary() + raise);
		}
		
		return emp;
	}
	
	public List<Employee> findAllEmployees(){
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
		return query.getResultList();
	}
}
