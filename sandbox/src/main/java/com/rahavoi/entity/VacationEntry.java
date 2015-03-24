package com.rahavoi.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class VacationEntry {
	@Temporal(TemporalType.DATE)
	private Calendar startDate;
	
	@Column(name = "DAYS")
	private int daysTaken;

	/**
	 * @return the startDate
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the daysTaken
	 */
	public int getDaysTaken() {
		return daysTaken;
	}

	/**
	 * @param daysTaken the daysTaken to set
	 */
	public void setDaysTaken(int daysTaken) {
		this.daysTaken = daysTaken;
	}
	
	
}
