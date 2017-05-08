package com.castleglobal.uber.services;

import java.util.List;

import com.castleglobal.uber.model.CabStatus;
import com.castleglobal.uber.model.CabType;
import com.castleglobal.uber.model.Coordinates;
import com.castleglobal.uber.model.Driver;
import com.castleglobal.uber.model.Location;
import com.castleglobal.uber.model.Trip;
import com.castleglobal.uber.rest.TripDto;

public interface IUberServices {

 String onboardCab(String carPlateNumber, Location currentLocation, CabStatus cabStatus, CabType cabType,
			Driver driver);
 
 String	onboardRider( Location riderLocation, String name, String mobileNumber);
 
 Trip requestCabByTypeAndLocation(CabType type,  String riderId ,Coordinates pickup , Coordinates drop);
 
 Location getLocationByCoordinates(Coordinates xy);
 
 void updateLocation(String cabId, Coordinates xy);
 
 void updateCabStatus( String cabId, CabStatus newStatus);
 
 void cancelBooking(Long tripId, boolean intiatedByRider , String comments);
 
 void completeTrip ( Long tripId);
 
 List<TripDto> showPastTrips (String id, boolean initiatedByRider );
}
