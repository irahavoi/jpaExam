package com.rahavoi.entity.listener;

import javax.persistence.PostLoad;

import com.rahavoi.entity.Employee;

public class EmployeeDebugListener {
	@PostLoad
	public void preLoad(Employee emp){
		System.out.println("Listener says: Employee " + emp.getName() + " was loaded!");
	}
}
