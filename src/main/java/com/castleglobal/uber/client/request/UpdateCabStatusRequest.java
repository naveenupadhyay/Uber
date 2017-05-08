package com.castleglobal.uber.client.request;

import com.castleglobal.uber.model.CabStatus;

public class UpdateCabStatusRequest {

	private String cabId;
	private CabStatus cabStatus;
	
	
	
	public UpdateCabStatusRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateCabStatusRequest(String cabId, CabStatus cabStatus) {
		super();
		this.cabId = cabId;
		this.cabStatus = cabStatus;
	}
	public String getCabId() {
		return cabId;
	}
	public CabStatus getCabStatus() {
		return cabStatus;
	}
	public void setCabId(String cabId) {
		this.cabId = cabId;
	}
	public void setCabStatus(CabStatus cabStatus) {
		this.cabStatus = cabStatus;
	}
	
	

}
