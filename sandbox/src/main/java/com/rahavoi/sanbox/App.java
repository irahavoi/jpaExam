package com.rahavoi.sanbox;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.rahavoi.entity.Employee;
import com.rahavoi.sanbox.service.EmployeeService;

/**
 * Hello world!
 */
public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeFactory");
        EntityManager em = emf.createEntityManager();
        
    	EmployeeService eService = new EmployeeService(em);
        
       // Employee employee = new Employee();
        //employee.setName("Pokahontas");
        //employee.setSalary(45000);
        
        //em.getTransaction().begin();
        //em.persist(employee);
        //Employee e0 = em.find(Employee.class, 0);
        //em.remove(employee);
        //em.getTransaction().commit();
        
        List<Employee> employees = eService.findAllEmployees();
        
        System.out.println("Found " + employees.size() + " employees.");
    }
}
