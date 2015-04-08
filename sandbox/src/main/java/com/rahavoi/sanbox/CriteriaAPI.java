package com.rahavoi.sanbox;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.rahavoi.entity.Employee;

public class CriteriaAPI {
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeFactory");
        EntityManager em = emf.createEntityManager();
        
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
		
		Root<Employee> emp = c.from(Employee.class);
		
		c.select(emp).where(cb.equal(emp.get("name"), "Joe"));
	}
}
