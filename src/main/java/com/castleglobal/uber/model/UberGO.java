package com.castleglobal.uber.model;

public class UberGO extends UberCar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3800050607408359625L;

	public UberGO(String carPlateNumber, Location currentLocation, CabStatus cabStatus, CabType cabType,
			Driver driver) {
		super(carPlateNumber, currentLocation, cabStatus, cabType, driver);
	}

}
