package com.castleglobal.uber.data;

import com.castleglobal.uber.model.CabType;
import com.castleglobal.uber.model.Location;
import com.castleglobal.uber.model.Rider;
import com.castleglobal.uber.model.Trip;
import com.castleglobal.uber.model.UberCar;

public interface IUberDataService {

	void addUberCarToMap(UberCar car);

	Rider addRider(Rider rider);

	UberCar deQueueAvailableCab(CabType type, Location location);

	void addTrip(Trip trip);

	UberCar getCabById(String cabId);

	Location updateLocation(UberCar car, Location location);

	Trip getTripById(Long tripId);

	Rider getRiderById(String riderId);
	


}
