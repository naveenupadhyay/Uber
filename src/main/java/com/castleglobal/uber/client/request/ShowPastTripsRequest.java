package com.castleglobal.uber.client.request;

public class ShowPastTripsRequest {
	private String id;
	private boolean initiatedByRider;
	
	public String getId() {
		return id;
	}
	public boolean isInitiatedByRider() {
		return initiatedByRider;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setInitiatedByRider(boolean initiatedByRider) {
		this.initiatedByRider = initiatedByRider;
	}
	
	
}
