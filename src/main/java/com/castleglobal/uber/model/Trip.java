package com.castleglobal.uber.model;

import java.io.Serializable;

public class Trip implements Serializable{
	private Long id ;
	private String riderId;
	private String cabId;
    private Coordinates pickup;
    private Coordinates dropOff;
    private BillingInfo bill;
    private TripStatus tripStatus;
    
    
	public Trip(Long tripId, String riderId, String cabId, Coordinates pickup, Coordinates dropOff) {
		super();
		this.id = IDGenerator.tripId.incrementAndGet();
		this.riderId = riderId;
		this.cabId = cabId;
		this.pickup = pickup;
		this.dropOff = dropOff;
		this.tripStatus = TripStatus.INITIATED;
	}
	public BillingInfo getBill() {
		return bill;
	}
	public void setBill(BillingInfo bill) {
		this.bill = bill;
	}
	public long getId() {
		return id;
	}
	public String getRiderId() {
		return riderId;
	}
	public String getCabId() {
		return cabId;
	}
	public Coordinates getPickup() {
		return pickup;
	}
	public Coordinates getDropOff() {
		return dropOff;
	}
	
	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}
	public void setCabId(String cabId) {
		this.cabId = cabId;
	}
	public void setPickup(Coordinates pickup) {
		this.pickup = pickup;
	}
	public void setDropOff(Coordinates dropOff) {
		this.dropOff = dropOff;
	}
	public TripStatus getTripStatus() {
		return tripStatus;
	}
	public void setTripStatus(TripStatus tripStatus) {
		this.tripStatus = tripStatus;
	}
	
    
}
