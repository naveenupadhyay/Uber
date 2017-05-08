package com.castleglobal.uber.client.request;

import com.castleglobal.uber.model.Location;

import groovy.transform.Immutable;

@Immutable
public class OnboardRiderRequest {

	private Location riderLocation;
	private String name;
	private String mobile;
	
	
	public OnboardRiderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OnboardRiderRequest(Location riderLocation, String name, String mobile) {
		super();
		this.riderLocation = riderLocation;
		this.name = name;
		this.mobile = mobile;
	}
	public Location getRiderLocation() {
		return riderLocation;
	}


	public String getName() {
		return name;
	}


	public String getMobile() {
		return mobile;
	}


	public void setRiderLocation(Location riderLocation) {
		this.riderLocation = riderLocation;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		return "OnboardRiderRequest [riderLocation=" + riderLocation + ", name=" + name + ", mobile=" + mobile + "]";
	}


	
	
	
}
