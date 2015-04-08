package com.rahavoi.sanbox.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.rahavoi.entity.Department;
import com.rahavoi.entity.Employee;
import com.rahavoi.entity.Project;

public class EmployeeService {
	private EntityManager em;

	public EmployeeService(EntityManager em) {
		this.em = em;
	}

	public Employee createEmployee(String name, Long salary) {
		Employee emp = new Employee();
		emp.setName(name);
		emp.setSalary(salary);

		em.persist(emp);

		return emp;
	}

	public void removeEmployee(int id) {
		Employee emp = findEmployee(id);
		if (emp != null) {
			em.remove(emp);
		}
	}

	public Employee findEmployee(int id) {
		return em.find(Employee.class, id);
	}

	public Employee raiseEmployeeSalary(int id, long raise) {
		Employee emp = em.find(Employee.class, id);

		if (emp != null) {
			emp.setSalary(emp.getSalary() + raise);
		}

		return emp;
	}

	public List<Employee> findAllEmployees() {
		TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e",
				Employee.class);
		return query.getResultList();
	}

	public List<Employee> findEmployees(String name, String deptName,
			String projectName, String city) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> c = cb.createQuery(Employee.class);
		Root<Employee> emp = c.from(Employee.class);
		c.select(emp);
		c.distinct(true);
		Join<Employee, Project> project = emp.join("projects", JoinType.LEFT);
		List<Predicate> criteria = new ArrayList<Predicate>();
		if (name != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "name");
			criteria.add(cb.equal(emp.get("name"), p));
		}
		if (deptName != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "dept");
			criteria.add(cb.equal(emp.get("dept").get("name"), p));
		}
		if (projectName != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"project");
			criteria.add(cb.equal(project.get("name"), p));
		}
		if (city != null) {
			ParameterExpression<String> p = cb.parameter(String.class, "city");
			criteria.add(cb.equal(emp.get("address").get("city"), p));
		}
		if (criteria.size() == 0) {
			throw new RuntimeException("no criteria");
		} else if (criteria.size() == 1) {
			c.where(criteria.get(0));
		} else {
			c.where(cb.and(criteria.toArray(new Predicate[0])));
		}
		TypedQuery<Employee> q = em.createQuery(c);
		if (name != null) {
			q.setParameter("name", name);
		}
		if (deptName != null) {
			q.setParameter("dept", deptName);
		}
		if (projectName != null) {
			q.setParameter("project", projectName);
		}
		if (city != null) {
			q.setParameter("city", city);
		}
		return q.getResultList();

	}
}
