package com.rahavoi.entity;

import java.util.Collection;
import java.util.Map;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import com.rahavoi.type.PhoneType;

@NamedQueries({
		@NamedQuery(name = "Employee.findAll", 
					query = "SELECT e FROM Employee e"),
		@NamedQuery(name = "Employee.findByPrimaryKey", 
					query = "SELECT e FROM Employee e WHERE e.id = :id"),
		@NamedQuery(name = "Employee.findByName", 
					query = "SELECT e FROM Employee e WHERE e.name = :name") })
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private long salary;
	
	@ElementCollection
	@CollectionTable(name = "EMP_PHONE")
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name = "PHONE_TYPE")
	@Column(name = "PHONE_NUM")
	private Map<PhoneType, String> phoneNumbers;
	
	//@CollectionTable(name="nickname") //<--optional. by default the table name would be EMPLOYEE_NICKNAMES
	//private Set<String> nicknames;
	
	//@ElementCollection(targetClass=VacationEntry.class) // <-- targetClass is needed only when collection is not generic
									// <-- @CollectionTable is skipped. Default name will be used: EMPLOYEE_VACATIONBOOKINGS
									// Join colummn will also be defaulted to EMPLOYEE_ID
	//private Collection vakationBookings;
	
	@Embedded // <-- optional annotation
	@AttributeOverrides({
		@AttributeOverride(name="state", column=@Column(name = "province"))
	})
	private Address address;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Department department;
	
	@OneToOne
	private ParkingSpace parkingSpace;
	
	@ManyToMany
	@JoinTable(name="EMP_PROJ",
			   joinColumns=@JoinColumn(name="EMP_ID"),
			   inverseJoinColumns=@JoinColumn(name="PROJ_ID"))
	private Collection<Project> projects;
	
	public Employee(){}

	public Employee(int id){
		this.id = id;
	}

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
	 * @return the salary
	 */
	public long getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(long salary) {
		this.salary = salary;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @return the parkingSpace
	 */
	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}

	/**
	 * @param parkingSpace the parkingSpace to set
	 */
	public void setParkingSpace(ParkingSpace parkingSpace) {
		this.parkingSpace = parkingSpace;
	}

	/**
	 * @return the projects
	 */
	public Collection<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the phoneNumbers
	 */
	public Map<PhoneType, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	/**
	 * @param phoneNumbers the phoneNumbers to set
	 */
	public void setPhoneNumbers(Map<PhoneType, String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	
}
