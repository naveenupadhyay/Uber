package com.castleglobal.uber.services;

import org.springframework.stereotype.Component;

import com.castleglobal.uber.model.CabStatus;
import com.castleglobal.uber.model.CabType;
import com.castleglobal.uber.model.Driver;
import com.castleglobal.uber.model.Location;
import com.castleglobal.uber.model.UberCar;
import com.castleglobal.uber.model.UberGO;
import com.castleglobal.uber.model.UberX;

@Component
public class UberCarFactory {
	
	public static UberCar createUberCar(String carPlateNumber, Location currentLocation, CabStatus cabStatus, CabType cabType,
			Driver driver){
		if(cabType == (CabType.UberGo))
			return new UberGO(carPlateNumber, currentLocation, cabStatus, cabType, driver);
		else
			return new UberX(carPlateNumber, currentLocation, cabStatus, cabType, driver);
	}

}
