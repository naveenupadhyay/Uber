package com.castleglobal.uber.model;

import java.io.Serializable;

public class Rider implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5621727371513450339L;
	private String id;
	private RiderStatus riderStatus;
	private Location riderLocation;
	private Trip trip;
	private String name;
	private String mobileNumber;

	
	public Rider( Location riderLocation, String name, String mobileNumber) {
		super();
		this.id = "RIDER" + IDGenerator.cabOrRiderId.incrementAndGet();
		this.riderStatus = RiderStatus.ONLINE;
		this.riderLocation = riderLocation;
		this.name = name;
		this.mobileNumber = mobileNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RiderStatus getRiderStatus() {
		return riderStatus;
	}
	public void setRiderStatus(RiderStatus riderStatus) {
		this.riderStatus = riderStatus;
	}
	public Location getRiderLocation() {
		return riderLocation;
	}
	public void setRiderLocation(Location riderLocation) {
		this.riderLocation = riderLocation;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public String getName() {
		return name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
