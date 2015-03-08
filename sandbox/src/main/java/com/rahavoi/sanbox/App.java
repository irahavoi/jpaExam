package com.rahavoi.sanbox;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeFactory");
        EntityManager em = emf.createEntityManager();
        
        Employee employee = new Employee();
        employee.setName("Joe");
        employee.setSalary(100000);
        
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        //em.find(Employee.class, 11);        
        //em.remove(employee);
    }
}
