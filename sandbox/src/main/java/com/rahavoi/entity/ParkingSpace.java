package com.rahavoi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ParkingSpace {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int lot;
	private String location;
	
	@OneToOne(mappedBy="parkingSpace")
	private Employee employee;
	
	/**
	 * @return the lot
	 */
	public int getLot() {
		return lot;
	}
	/**
	 * @param lot the lot to set
	 */
	public void setLot(int lot) {
		this.lot = lot;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
