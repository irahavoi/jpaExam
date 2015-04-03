package com.rahavoi.sanbox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rahavoi.entity.Employee;

public class QueriesExamples {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeFactory");
    private EntityManager em = emf.createEntityManager();
    
	public static void main(String[] args){
       QueriesExamples qe = new QueriesExamples();
       
       String pokahontas = "Pokahontas";
       Long pokahontasSalary = qe.insecureInefficientQuery(pokahontas);
       String joe = "Joe";
       Long joeSalary = qe.namedParametersQuery(joe);
       
       System.out.println(pokahontas + ": " + pokahontasSalary);
       System.out.println(joe + ": " + joeSalary);
       
       
       
       List<Employee> allEmployees = qe.namedQuery();
       
       for(Employee e : allEmployees){
    	   System.out.println("-------------------------------------------");
    	   System.out.println("Employee: " + e.getName());
    	   System.out.println("Salary: " + e.getSalary());
    	   System.out.println("Department: " + e.getDepartment().getName());
    	   System.out.println("-------------------------------------------");
       }
	}
	
	/**
	 * An example of a dynamic query having performance and security problems:
	 * Because names are concatenated into sString instead of parameter binding,
	 * a ne query is created each time (cannot cache).
	 * It is also vulnerable to sql injection attacks.
	 * @param em
	 */
	public Long insecureInefficientQuery(String name){
		String query = "SELECT e.salary FROM Employee e " + 
					   "WHERE e.name = '" + name + "'";
		
		return em.createQuery(query, Long.class).getSingleResult();
	}
	/**
	 * Still inefficiient but secure query.
	 * The parameters ar marshalled using jdbc api and handled directly by database.
	 * The text of the parameter is properly escaped.
	 * @param name
	 * @return
	 */
	public Long namedParametersQuery(String name){
		String query = "SELECT e.salary FROM Employee e " + 
				   "WHERE e.name = :empName";
	
		return em.createQuery(query, Long.class)
				.setParameter("empName", name)
				.getSingleResult();
	}
	
	/**
	 * Uses static named query "Employee.findAll" defined in the Employee class
	 * using @NamedQuery annotation.
	 * @return
	 */
	public List<Employee> namedQuery(){
		em.getTransaction().begin();
		List<Employee> result = em.createNamedQuery("Employee.findAll", Employee.class)
				.getResultList();
		
		//Returned entities are managed. If any changes are made on them 
		//before transaction commits, these changes will be persisted.
		for(Employee e : result){
			//e.setName(e.getName() + "test tx");
		}
		
		em.getTransaction().commit();
		return result;
	}
}
