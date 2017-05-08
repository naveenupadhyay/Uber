package com.castleglobal.uber.client.request;

import com.castleglobal.uber.model.Coordinates;

public class UpdateLocationRequest {

	private String cabId;
	private Coordinates xy;
	
	
	public UpdateLocationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateLocationRequest(String cabId, Coordinates xy) {
		super();
		this.cabId = cabId;
		this.xy = xy;
	}
	public String getCabId() {
		return cabId;
	}
	public Coordinates getXy() {
		return xy;
	}
	public void setCabId(String cabId) {
		this.cabId = cabId;
	}
	public void setXy(Coordinates xy) {
		this.xy = xy;
	}
	
	
}
