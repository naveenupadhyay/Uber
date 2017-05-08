package com.castleglobal.uber.model;

import java.io.Serializable;

public abstract class UberCar  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7743447418777355676L;
	private String cabId;
	private String carPlateNumber;
	private Location currentLocation;
	private CabStatus cabStatus = CabStatus.AVAILABLE;
	private CabType cabType;
	private Trip currentTrip;
	private Driver driver;
	
	protected UberCar(String carPlateNumber, Location currentLocation, CabStatus cabStatus, CabType cabType,
			Driver driver) {
		super();
		this.cabId = "CAB" + IDGenerator.STATE_CODE + IDGenerator.cabOrRiderId.incrementAndGet();
		this.carPlateNumber = carPlateNumber;
		this.currentLocation = currentLocation;
		this.cabStatus = cabStatus;
		this.cabType = cabType;
		this.driver = driver;
	}
	public String getCarPlateNumber() {
		return carPlateNumber;
	}
	public void setCarPlateNumber(String carPlateNumber) {
		this.carPlateNumber = carPlateNumber;
	}
	public String getCabId() {
		return cabId;
	}
	public void setCabId(String cabId) {
		this.cabId = cabId;
	}
	public Location getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}
	public CabStatus getCabStatus() {
		return cabStatus;
	}
	public void setCabStatus(CabStatus cabStatus) {
		this.cabStatus = cabStatus;
	}
	public CabType getCabType() {
		return cabType;
	}
	public void setCabType(CabType cabType) {
		this.cabType = cabType;
	}
	public Trip getCurrentTrip() {
		return currentTrip;
	}
	public void setCurrentTrip(Trip currentTrip) {
		this.currentTrip = currentTrip;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "UberCar [cabId=" + cabId + ", currentLocation=" + currentLocation + "]";
	}

	

	
	
}
