package com.castleglobal.uber.client.request;

import com.castleglobal.uber.model.CabStatus;
import com.castleglobal.uber.model.CabType;
import com.castleglobal.uber.model.Driver;
import com.castleglobal.uber.model.Location;

import groovy.transform.Immutable;


@Immutable
public class OnboardCabRequest {

	private String carPlateNumber;
	private Location currentLocation;
	private CabStatus cabStatus;
	private CabType cabType;
	private Driver driver;
	
	
	public OnboardCabRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OnboardCabRequest(String carPlateNumber, Location currentLocation, CabStatus cabStatus, CabType cabType,
			Driver driver) {
		super();
		this.carPlateNumber = carPlateNumber;
		this.currentLocation = currentLocation;
		this.cabStatus = cabStatus;
		this.cabType = cabType;
		this.driver = driver;
	}
	public String getCarPlateNumber() {
		return carPlateNumber;
	}
	public Location getCurrentLocation() {
		return currentLocation;
	}
	public CabStatus getCabStatus() {
		return cabStatus;
	}
	public CabType getCabType() {
		return cabType;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setCarPlateNumber(String carPlateNumber) {
		this.carPlateNumber = carPlateNumber;
	}
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	public void setCabStatus(CabStatus cabStatus) {
		this.cabStatus = cabStatus;
	}
	public void setCabType(CabType cabType) {
		this.cabType = cabType;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	@Override
	public String toString() {
		return "OnboardCabRequest [carPlateNumber=" + carPlateNumber + ", currentLocation=" + currentLocation
				+ ", cabType=" + cabType + "]";
	}
	
	
}
