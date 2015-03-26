package com.rahavoi.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	
	@ManyToMany
	@JoinTable(name = "DEPT_EMP",
		joinColumns = @JoinColumn(name = "DEPT_ID"),
		inverseJoinColumns = @JoinColumn(name = "EMP_ID"))
	@MapKeyColumn(name = "CUB_ID")
	//@MapKey(name = "id") //<-- If no map key is specified, entity's id is used by default.
	private Map<String, Employee> employeesByCubicle;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * @return the employeesByCubicle
	 */
	public Map<String, Employee> getEmployeesByCubicle() {
		return employeesByCubicle;
	}

	/**
	 * @param employeesByCubicle the employeesByCubicle to set
	 */
	public void setEmployeesByCubicle(Map<String, Employee> employeesByCubicle) {
		this.employeesByCubicle = employeesByCubicle;
	}
	
}
