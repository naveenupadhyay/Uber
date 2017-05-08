package com.castleglobal.uber.model;

import java.io.Serializable;

public class Driver implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2264513739675998185L;
	private Long id;
	private String name;
	private String contactNumber;
	private String licenseNumber;
	private String licenseImgURL;
	
	
	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Driver(String name, String contactNumber, String licenseNumber) {
		super();
		this.id = IDGenerator.driverId.incrementAndGet();
		this.name = name;
		this.contactNumber = contactNumber;
		this.licenseNumber = licenseNumber;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getLicenseImgURL() {
		return licenseImgURL;
	}
	public void setLicenseImgURL(String licenseImgURL) {
		this.licenseImgURL = licenseImgURL;
	}
	
	

}
