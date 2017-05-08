package com.castleglobal.uber.model;

public class UberX extends UberCar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5357293812907461062L;

	public UberX(String carPlateNumber, Location currentLocation, CabStatus cabStatus, CabType cabType,
			Driver driver) {
		super(carPlateNumber, currentLocation, cabStatus, cabType, driver);
	}

}
