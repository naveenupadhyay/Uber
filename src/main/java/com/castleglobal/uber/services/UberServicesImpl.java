package com.castleglobal.uber.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castleglobal.uber.data.IUberDataService;
import com.castleglobal.uber.model.CabStatus;
import com.castleglobal.uber.model.CabType;
import com.castleglobal.uber.model.Coordinates;
import com.castleglobal.uber.model.Driver;
import com.castleglobal.uber.model.IDGenerator;
import com.castleglobal.uber.model.Location;
import com.castleglobal.uber.model.Rider;
import com.castleglobal.uber.model.RiderStatus;
import com.castleglobal.uber.model.Trip;
import com.castleglobal.uber.model.TripStatus;
import com.castleglobal.uber.model.UberCar;
import com.castleglobal.uber.rest.TripDto;

@Service
public class UberServicesImpl implements IUberServices{
	
	private final static Logger LOG = LoggerFactory.getLogger(UberServicesImpl.class);
	
	@Autowired
	private IUberDataService uberData;
	@Autowired
	private UberCarFactory carFactory;

	public String onboardCab(String carPlateNumber, Location currentLocation, CabStatus cabStatus, CabType cabType,
			Driver driver) {
		UberCar car = UberCarFactory.createUberCar(carPlateNumber, currentLocation, cabStatus, cabType, driver);
		uberData.addUberCarToMap(car);
		//	LOG.info("Cab " + car.getCabId() + " onboarded");
		return car.getCabId();
	}

	public String onboardRider( Location riderLocation, String name, String mobileNumber) {
		Rider rider = new Rider(riderLocation, name, mobileNumber);
		uberData.addRider( rider);
		return rider.getId();
	}

	public Trip requestCabByTypeAndLocation(CabType type,  String riderId ,Coordinates pickup , Coordinates drop) {
		UberCar car = uberData.deQueueAvailableCab(type,new Location(pickup));
		if(car == null){
			LOG.warn("Could not assign cab");
			return null;
		}
		car.setCabStatus(CabStatus.BOOKED);
		Trip trip = new Trip(IDGenerator.tripId.incrementAndGet(), riderId, car.getCabId(), pickup, drop);
		 uberData.addTrip(trip);
		return trip;
	}

	public Location getLocationByCoordinates(Coordinates xy) {
		return new Location(xy);
	}

	public void updateLocation(String cabId, Coordinates xy) {
	  UberCar car =	uberData.getCabById(cabId);
	  uberData.updateLocation(car, new Location(xy));
	}

	public void updateCabStatus(String cabId ,CabStatus newStatus) {
		//Not implemented
		  UberCar car =	uberData.getCabById(cabId);
		  car.setCabStatus(newStatus);
	}

	public void cancelBooking(Long tripId, boolean intiatedByRider, String comments) {
		Trip trip = uberData.getTripById(tripId);
		Rider rider = uberData.getRiderById(trip.getRiderId());
		UberCar car = uberData.getCabById(trip.getCabId());
		trip.setTripStatus(TripStatus.CANCELLED);
		rider.setRiderStatus(RiderStatus.CANCELLED_THE_RIDE);
		car.setCabStatus(CabStatus.AVAILABLE);
		uberData.addUberCarToMap(car);
	}

	public void completeTrip( Long tripId) {
		Trip trip = uberData.getTripById(tripId);
		trip.setTripStatus(TripStatus.COMPLETED);
		 UberCar car = uberData.getCabById(trip.getCabId());
		 car.setCurrentLocation(new Location(trip.getDropOff()));
		uberData.addUberCarToMap(car);
		Rider rider = uberData.getRiderById(trip.getRiderId());
		rider.setRiderStatus(RiderStatus.ONLINE);
	}

	public List<TripDto> showPastTrips(String id, boolean initiatedByRider) {
		//Not implemented
		// create a map in uberData class.
		return null;
	}

}
