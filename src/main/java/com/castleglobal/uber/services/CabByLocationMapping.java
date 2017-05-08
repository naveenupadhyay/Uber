package com.castleglobal.uber.services;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.castleglobal.uber.model.CabType;
import com.castleglobal.uber.model.Location;
import com.castleglobal.uber.model.UberCar;

@Component
public class CabByLocationMapping implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2904747962854978844L;

	Map<CabType,ConcurrentMap<Location , Set<UberCar>>> uberCarAvailabilityMap = new HashMap<CabType, ConcurrentMap<Location,Set<UberCar>>>();
	
	private final static Logger LOG = LoggerFactory.getLogger(CabByLocationMapping.class);
	
	public boolean addUberCarToMap(UberCar car){
		CabType cabType = car.getCabType();
		ConcurrentMap<Location,Set<UberCar>> availabilityMap = uberCarAvailabilityMap.get(car.getCabType());
		if(availabilityMap == null){
			availabilityMap = new ConcurrentHashMap<Location,Set<UberCar>>();
			uberCarAvailabilityMap.put(cabType, availabilityMap);
		}
		Set<UberCar> carSet = availabilityMap.get(car.getCurrentLocation());
		if(carSet == null){
			carSet = new LinkedHashSet<UberCar>();
			availabilityMap.put(car.getCurrentLocation(), carSet);
		}
		   carSet.add(car);
		   
		LOG.info(" Cab {} added to uberCarAvailabilityMap", car.getCabId());
		return true;
		
	}


	public UberCar deQueueAvailableCab(CabType type, Location location) {
		Set<UberCar> availableCars = uberCarAvailabilityMap.get(type).get(location);
		if(availableCars == null)
		{
			//do BFS instead
			LOG.warn(" No available cabs found in given polygon .. try another one using BFS .. returning null for now" );
			LOG.warn("Available polygons {} " , uberCarAvailabilityMap.get(type).keySet());
			return null;
		}
		
		UberCar carToAssign =  availableCars.iterator().next();
		LOG.info("Available cars  {} {}", availableCars.size() , availableCars.toString());
		LOG.info("Assigning car {}",  carToAssign);
		availableCars.remove(carToAssign);
		return carToAssign;
	}
	
	public Location updateLocation(UberCar car, Location newLocation){
		Set<UberCar> uberCars = uberCarAvailabilityMap.get(car.getCabType()).get(car.getCurrentLocation());
		uberCars.remove(car);
		addUberCarToMap(car);
		car.setCurrentLocation(newLocation);
		return newLocation;
	}
}
