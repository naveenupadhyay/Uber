package com.castleglobal.uber.model;

public enum CabType {
	UberGo(0.0,4) , UberX(1.0,4),UberXL(5.0,6);
	
	private Double minimumFare;
	private Integer maximumCapacity;
	
	private CabType(Double minFare, Integer maxCapacity){
		this.minimumFare = minFare;
		this.maximumCapacity = maxCapacity;
	}
	public Double getMinimumFare() {
		return minimumFare;
	}
	public void setMinimumFare(Double minimumFare) {
		this.minimumFare = minimumFare;
	}
	public Integer getMaximumCapacity() {
		return maximumCapacity;
	}
	public void setMaximumCapacity(Integer maximumCapacity) {
		this.maximumCapacity = maximumCapacity;
	}
	
	
}
