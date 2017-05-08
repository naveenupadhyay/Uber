package com.castleglobal.uber.client.request;

import com.castleglobal.uber.model.CabType;
import com.castleglobal.uber.model.Coordinates;

import groovy.transform.Immutable;
/*
 * these are request objects meant for clients to call our restful webservices.
 * Please note the request classes should NOT use underlining model objects and should rather use SROs meant for such purposes.
 * Due to lack of time i have directly used model entities e.g. location , coordinates etc.
 */
@Immutable
public class BookCabRequest {

	private CabType cabType;
	private String riderId;
	private Coordinates pickup;
	private Coordinates drop;

	public BookCabRequest(CabType cabType,  String riderId, Coordinates pickup, Coordinates drop) {
		super();
		this.cabType = cabType;
		this.riderId = riderId;
		this.pickup = pickup;
		this.drop = drop;
	}
	public BookCabRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CabType getCabType() {
		return cabType;
	}
	public String getRiderId() {
		return riderId;
	}
	public Coordinates getPickup() {
		return pickup;
	}
	public Coordinates getDrop() {
		return drop;
	}
	public void setCabType(CabType cabType) {
		this.cabType = cabType;
	}
	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}
	public void setPickup(Coordinates pickup) {
		this.pickup = pickup;
	}
	public void setDrop(Coordinates drop) {
		this.drop = drop;
	}
	@Override
	public String toString() {
		return "BookCabRequest [cabType=" + cabType + ", riderId=" + riderId + ", pickup=" + pickup + ", drop=" + drop
				+ "]";
	}
	
	
	
}
