package com.castleglobal.uber.rest;

import com.castleglobal.uber.model.Coordinates;
import com.castleglobal.uber.model.TripStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class TripDto {

	private Long id ;
	private String riderId;
	private String cabId;
    private Coordinates pickup;
    private Coordinates dropOff;
    private TripStatus tripStatus;
    
    
	public TripDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
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
	public TripStatus getTripStatus() {
		return tripStatus;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setTripStatus(TripStatus tripStatus) {
		this.tripStatus = tripStatus;
	}
	@Override
	public String toString() {
		return "TripDto [id=" + id + ", riderId=" + riderId + ", cabId=" + cabId + ", pickup=" + pickup + ", dropOff="
				+ dropOff + ", tripStatus=" + tripStatus + "]";
	}
    
    
}
