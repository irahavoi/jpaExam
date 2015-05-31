package com.rahavoi.criteria.api;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.rahavoi.entity.Department;
import com.rahavoi.entity.Employee;
import com.sun.org.apache.bcel.internal.generic.SALOAD;

public class Runner {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeFactory");
	private static EntityManager em = emf.createEntityManager();
	
	public static void main(String[] args){
		List<Employee> result = getByDeptName("IT");
		
		for(Employee e : result){
			System.out.println(e.getName() + ", " + e.getDepartment().getName()
					+ ", " + e.getSalary());
		}
	}
	
	private static void testTuple(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> tupleQuery = cb.createTupleQuery();
		Root<Employee> emp = tupleQuery.from(Employee.class);
		
		//TODO:
		
		List<Tuple> result = em.createQuery(tupleQuery).getResultList();
		
		System.out.println(result);
		
		
	}
	
	private static List<Employee> getByDeptName(String deptName){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> emp = cq.from(Employee.class);
		
		ParameterExpression<String> deptParam = cb.parameter(String.class, "deptName");
		
		cq.select(emp)
			.where(
					emp.get("department").get("name").in("IT", "hr", "financial"),
					cb.equal(emp.get("department").get("name"), deptParam));
		
		TypedQuery<Employee> q = em.createQuery(cq);
		
		q.setParameter(deptParam, deptName);
		
		return q.getResultList();
	}
}
