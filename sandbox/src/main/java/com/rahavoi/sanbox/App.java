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
        employee.setName("Pokahontas");
        employee.setSalary(45000);
        
        em.getTransaction().begin();
        em.persist(employee);
        //Employee e0 = em.find(Employee.class, 0);
        em.getTransaction().commit();
        //employee.getId();
        //em.close();
        //em.find(Employee.class, 11);        
        //em.remove(employee);
    }
}
