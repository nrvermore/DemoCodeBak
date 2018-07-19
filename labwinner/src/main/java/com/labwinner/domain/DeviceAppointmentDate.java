package com.labwinner.domain;

import java.util.List;


public class DeviceAppointmentDate implements java.io.Serializable {

	private List<String> date;
	
	private List<String> haveAppointmentList;
	
	// Constructors

	/** default constructor */
	public DeviceAppointmentDate() {
	}

	public DeviceAppointmentDate(List<String> date,
			List<String> haveAppointmentList) {
		super();
		this.date = date;
		this.haveAppointmentList = haveAppointmentList;
	}

	public List<String> getDate() {
		return date;
	}

	public void setDate(List<String> date) {
		this.date = date;
	}

	public List<String> getHaveAppointmentList() {
		return haveAppointmentList;
	}

	public void setHaveAppointmentList(List<String> haveAppointmentList) {
		this.haveAppointmentList = haveAppointmentList;
	}

}