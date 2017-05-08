package com.castleglobal.uber.client.request;

import groovy.transform.Immutable;

@Immutable
public class CancelBookingRequest {
	private Long tripId;
	private boolean intiatedByRider;
	private String comments;
	
	public CancelBookingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CancelBookingRequest(Long tripId, boolean intiatedByRider, String comments) {
		super();
		this.tripId = tripId;
		this.intiatedByRider = intiatedByRider;
		this.comments = comments;
	}
	public Long getTripId() {
		return tripId;
	}
	public boolean isIntiatedByRider() {
		return intiatedByRider;
	}
	public String getComments() {
		return comments;
	}
	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}
	public void setIntiatedByRider(boolean intiatedByRider) {
		this.intiatedByRider = intiatedByRider;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "CancelBookingRequest [tripId=" + tripId + ", intiatedByRider=" + intiatedByRider + ", comments="
				+ comments + "]";
	}
	
	
}
