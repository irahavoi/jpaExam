package com.rahavoi.criteria.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.rahavoi.entity.Department;
import com.rahavoi.entity.Employee;

public class Runner {
	public static void main(String[] args){
		List<Employee> result = getByDeptName("IT");
		
		for(Employee e : result){
			System.out.println(e.getName() + ", " + e.getDepartment().getName()
					+ ", " + e.getSalary());
		}
	}
	
	private static List<Employee> getByDeptName(String deptName){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeFactory");
        EntityManager em = emf.createEntityManager();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> emp = cq.from(Employee.class);
		
		ParameterExpression<String> deptParam = cb.parameter(String.class, "deptName");
		
		cq.select(emp)
			.where(cb.equal(emp.get("department").get("name"), deptParam));
		
		TypedQuery<Employee> q = em.createQuery(cq);
		
		q.setParameter(deptParam, deptName);
		
		return q.getResultList();
	}
}
