package com.rahavoi.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@ManyToMany
	private Collection<Employee> employees;
	
	
	@ElementCollection
	@OrderColumn(name="task_num") //<-- Column to save the ordered state of the tasks list
	private List<String> tasks;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the employees
	 */
	public Collection<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}
	
	
	
}
