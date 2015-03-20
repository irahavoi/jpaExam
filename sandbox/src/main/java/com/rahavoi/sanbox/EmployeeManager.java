package com.rahavoi.sanbox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.rahavoi.entity.Employee;

public class EmployeeManager {
	private EntityManagerFactory emf;
	private EntityManager em;

	public EmployeeManager() {
		this.init();
	}

	private void init() {
		emf = Persistence.createEntityManagerFactory("EmployeeFactory");
		em = emf.createEntityManager();
	}

	/**
	 * Creates an employee Entity
	 * 
	 * @param id
	 * @param name
	 * @param salary
	 * @return
	 */
	public Employee createEmployee(int id, String name, long salary) {
		Employee emp = new Employee(id);
		emp.setName(name);
		emp.setSalary(salary);
		em.persist(emp);
		return emp;
	}

	public Employee findEmployee(int id) {
		return em.find(Employee.class, id);
	}

	public void removeEmployee(int id) {
		Employee emp = em.find(Employee.class, id);
		if (emp != null) {
			em.remove(emp);
		}
	}

	public Employee raiseEmployeeSalary(int id, long raise) {
		Employee emp = em.find(Employee.class, id);
		if (emp != null) {
			emp.setSalary(emp.getSalary() + raise);
		}
		return emp;
	}

	public List<Employee> findAllEmployees() {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e",
				Employee.class);
		return query.getResultList();
	}

}
