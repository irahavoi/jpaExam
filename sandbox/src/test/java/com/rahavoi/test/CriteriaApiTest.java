package com.rahavoi.test;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.rahavoi.entity.Employee;
import com.rahavoi.type.PhoneType;

public class CriteriaApiTest {
	public static void main(String args []){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeFactory");
        EntityManager em = emf.createEntityManager();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root emp = cq.from(Employee.class);
        
        /*
         * Join Types:
         * JoinType.LEFT,
         * JoinType.RIGHT,
         * JoinType.INNER,
         */
        
        emp.join("phoneNumbers", JoinType.LEFT);
        cq.select(emp);
        
        em.createQuery(cq).getResultList();
        
    //    savePhone(em);
        
        em.close();
        emf.close();
	}
	
	public static void savePhone(EntityManager em){
		em.getTransaction().begin();
		Employee emp = em.find(Employee.class, 2);
		Map<PhoneType, String> phones = new HashMap<PhoneType, String>();
		phones.put(PhoneType.MOBILE, "111 222 333");
		emp.setPhoneNumbers(phones);
		em.merge(emp);
		
		em.getTransaction().commit();
		
		emp.getPhoneNumbers();
		
	}
	
	
	/*
     * Join Types:
     * JoinType.LEFT,
     * JoinType.RIGHT,
     * JoinType.INNER,
     */
	
	
	/*
	 * LockModeTypes:
	 * READ : Same as OPTIMISTIC 
	 * WRITE: Same as OPTIMISTIC_FORCE_INCREMENT
	 * OPTIMISTIC : Prevents dirty read and non-repeatable read
	 * OPTIMISTIC_FORCE_INCREMENT: Prevents dirty read and non-repeatable read as well as increments the version of the object.
	 * 
	 * PESSIMISTIC_READ : Acquires the lock on the row in the database + Prevents dirty read and non-repeatable read 
	 * PESSIMISTIC_WRITE : Acquires the lock on the row in the database + Prevents dirty read and non-repeatable read
	 * 
	 * PESSIMISTIC_FORCE_INCREMENT:  Acquires the lock on the row in the database + Prevents dirty read and non-repeatable read + Increments the version of the object. Support for this lock mode for non-versioned objects is not required.  
	 * NONE:  No Lock.
	 */
}
