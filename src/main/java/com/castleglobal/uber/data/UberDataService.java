package com.castleglobal.uber.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castleglobal.uber.model.BillingInfo;
import com.castleglobal.uber.model.CabType;
import com.castleglobal.uber.model.IDGenerator;
import com.castleglobal.uber.model.Location;
import com.castleglobal.uber.model.Rider;
import com.castleglobal.uber.model.Trip;
import com.castleglobal.uber.model.UberCar;
import com.castleglobal.uber.services.CabByLocationMapping;

@Service
public class UberDataService implements IUberDataService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6235537518171471175L;

	@Autowired
	private CabByLocationMapping allCabLocationMappings;
	
	private Map<String/*cabId*/,UberCar> allCarsMap = new HashMap<String, UberCar>() ;
	
	private Map<String/*riderId*/,Rider> allRiders = new HashMap<String, Rider>();
	
	private Map<Long,Trip> allTrips = new HashMap<Long, Trip>();

	private Map<Long,BillingInfo> allBillInfos  = new HashMap<Long, BillingInfo>();
	
	private IDGenerator idGenerator;
	
	private final static Logger LOG = LoggerFactory.getLogger(UberDataService.class);

	
	@PostConstruct
	void init() throws ClassNotFoundException, IOException{
		//load up from fileSystem
		try {
			FileInputStream fis = new FileInputStream(new File("uberData.ser"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			UberDataService data =  (UberDataService) ois.readObject();
			this.allCarsMap = data.allCarsMap;
			this.allRiders = data.allRiders;
			this.allTrips = data.allTrips;
			this.idGenerator = data.idGenerator;
			this.allCabLocationMappings = data.allCabLocationMappings;
		} catch (Exception e) {
			LOG.info("This is the first invocation of the application" );
		}
	}
	
	@PreDestroy
	void serialize() throws IOException{
		FileOutputStream fos =
                new FileOutputStream(new File("uberData.ser"));
		 ObjectOutputStream oos = new ObjectOutputStream(fos);
		 oos.writeObject(this);
		 oos.close();
	}

	public void setAllCabMappings(CabByLocationMapping allCabMappings) {
		this.allCabLocationMappings = allCabMappings;
	}

	public void setAllCarsMap(Map<String, UberCar> allCarsMap) {
		this.allCarsMap = allCarsMap;
	}

	public void setAllRiders(Map<String, Rider> allRiders) {
		this.allRiders = allRiders;
	}

	public void setAllTrips(Map<Long, Trip> allTrips) {
		this.allTrips = allTrips;
	}

	public void setAllBillInfos(Map<Long, BillingInfo> allBillInfos) {
		this.allBillInfos = allBillInfos;
	}

	public void addUberCarToMap(UberCar car) {
		allCarsMap.put(car.getCabId(), car);
		allCabLocationMappings.addUberCarToMap(car);
	}

	public UberCar deQueueAvailableCab(CabType type, Location location) {

		return allCabLocationMappings.deQueueAvailableCab(type, location);
	}

	public Location updateLocation(UberCar car, Location location) {
		return allCabLocationMappings.updateLocation(car,location);
	}

	public UberCar getCabById(String cabId) {
		return allCarsMap.get(cabId);
	}

	public Trip getTripById(Long tripId) {
		return allTrips.get(tripId);
	}

	public Rider getRiderById(String riderId) {
		return allRiders.get(riderId);
	}

	public Rider addRider(Rider rider) {
		return allRiders.put(rider.getId(), rider);
	}

	public void addTrip(Trip trip) {
		allTrips.put(trip.getId(), trip);
	}
	
	
	
}
