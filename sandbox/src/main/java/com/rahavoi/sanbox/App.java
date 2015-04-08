package com.rahavoi.sanbox;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.rahavoi.entity.Department;
import com.rahavoi.entity.Employee;
import com.rahavoi.entity.ParkingSpace;
import com.rahavoi.sanbox.service.EmployeeService;

/**
 * Hello world!
 * 
 * The two rules, then, for bidirectional one-to-one associations are the following:
	• The @JoinColumn annotation goes on the mapping of the entity that is mapped to
	the table containing the join column, or the owner of the relationship. This might
	be on either side of the association.
	• The mappedBy element should be specified in the @OneToOne annotation in the
	entity that does not define a join column, or the inverse side of the relationship.

 */
public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeFactory");
        EntityManager em = emf.createEntityManager();
        
        EmployeeService eService = new EmployeeService(em);
       
        List<Employee> result = eService.findEmployees("Joet", null, null, null);
        //List<Employee> allEmployees = eService.findAllEmployees();
        
      /* Employee employee = new Employee();
       employee.setName("Joe");
       employee.setSalary(90000);
       
       ParkingSpace parking = new ParkingSpace();
       parking.setLocation("5th floor");
       parking.setLot(100);
       
       Department department = new Department();
       department.setName("IT");
       
       employee.setDepartment(department);
       employee.setParkingSpace(parking);
       
       List<Employee> allEmployees = new ArrayList<Employee>();
       //department.setEmployees(allEmployees);
       
       em.getTransaction().begin();
       
       em.persist(employee);
       em.persist(department);
       em.persist(parking);
       */
       
       em.getTransaction().commit();
       
       em.close();
       emf.close();
       
   }
}
