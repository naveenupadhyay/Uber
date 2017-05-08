package com.castleglobal.uber.model;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class IDGenerator implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5867795586420939158L;

	public static final String STATE_CODE = "CA";
	
	public static AtomicLong tripId = new AtomicLong();
	
	public static AtomicLong cabOrRiderId = new AtomicLong();
	
	public static AtomicLong driverId = new AtomicLong();
	
}
