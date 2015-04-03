package com.rahavoi.sanbox;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class QueriesExamples {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeFactory");
    private EntityManager em = emf.createEntityManager();
    
	public static void main(String[] args){
       QueriesExamples qe = new QueriesExamples();
       
       Long salary = qe.insecureInefficientQuery("Pokahontas");
       System.out.println(salary);
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
}
