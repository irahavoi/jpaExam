package com.rahavoi.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String street;
	private String city;
	private String state;
	@Column(name = "ZIP_CODE")
	private String zip;
	// ...
}