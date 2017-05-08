package com.castleglobal.uber.test;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.castleglobal.uber.Application;
import com.castleglobal.uber.client.request.BookCabRequest;
import com.castleglobal.uber.client.request.CancelBookingRequest;
import com.castleglobal.uber.client.request.CompleteTripRequest;
import com.castleglobal.uber.client.request.OnboardCabRequest;
import com.castleglobal.uber.client.request.OnboardRiderRequest;
import com.castleglobal.uber.client.request.UpdateCabStatusRequest;
import com.castleglobal.uber.client.request.UpdateLocationRequest;
import com.castleglobal.uber.data.IUberDataService;
import com.castleglobal.uber.model.CabStatus;
import com.castleglobal.uber.model.CabType;
import com.castleglobal.uber.model.Coordinates;
import com.castleglobal.uber.model.Driver;
import com.castleglobal.uber.model.Location;
import com.castleglobal.uber.model.UberCar;
import com.castleglobal.uber.rest.TripDto;
import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class UberRestServiceTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	IUberDataService uberData;
	
	private String riderId;
	private String cabId;
	private Long tripId;
	private Gson gson = new Gson();
	

	private final static Logger LOG = LoggerFactory.getLogger(UberRestServiceTest.class);
	
	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext)
				.addFilters(springSecurityFilterChain)
	              .build();
		// SecurityContextHolder.getContext().setAuthentication(new TestingAuthenticationToken(currentUser, null));
	}
	
	@Test
	public void onboardCabTest() throws Exception{
	
		String[] carPlates = {
				 "SWIG101",
				  "BOOM202",
				  "TEST303",
				  "MIKE404",
				  "SCREQ505",
				  "TEST606",
				  "TEST707",
				  "TEST808",
				  "TEST909",
				  "TEST1010",
				  "TEST11",
				  "TEST12",
				  "TEST13",
				  "TEST14",
				  "TEST15",
				   "TEST16"
		};
		for (long i = 0 ; i < 16 ; i ++){
			Location location = new Location(new Coordinates(i, i));
			
			OnboardCabRequest request = new OnboardCabRequest(carPlates[(int) i], location, CabStatus.AVAILABLE, CabType.UberGo, new Driver());
			LOG.info("");
			MvcResult mvcResult = mockMvc
					.perform(post("/public/rest/uber/onboardCab").contentType(
				            MediaType.APPLICATION_JSON).content(gson.toJson(request))).andReturn();
			cabId = mvcResult.getResponse().getContentAsString();
			LOG.info("Cab {} onboarded for {}", cabId , location);
		}
	}
	@Test
	public void onboardRiderTest() throws Exception {
		Location location = new Location(new Coordinates(10L, 10L));
		OnboardRiderRequest request = new OnboardRiderRequest(location, "Naveen Upadhyay", "+91 988-690-2226");
		MvcResult mvcResult = mockMvc.perform(post("/public/rest/uber/onboardRider").contentType(
	            MediaType.APPLICATION_JSON).content(gson.toJson(request))).andReturn();
		
	    riderId = mvcResult.getResponse().getContentAsString();
		LOG.info("Rider's onboarded : {}" , riderId);
		
	}
	
	@Test
	public void bookCabTest() throws Exception {
		//String cabType, Location location, String riderId, Coordinates pickup,
		//Coordinates drop
	    onboardCabTest();
		onboardRiderTest();
		Gson gson = new Gson();
		
		Coordinates pickup = new Coordinates(5L, 5L);
		Coordinates drop = new Coordinates(9L, 9L);
		
		BookCabRequest request = new BookCabRequest(CabType.UberGo,  riderId, pickup, drop);
		MvcResult mvcResult = mockMvc.perform(post("/public/rest/uber/bookCab").contentType(
	            MediaType.APPLICATION_JSON).content(gson.toJson(request))).andReturn();
		
		TripDto trip = gson.fromJson(mvcResult.getResponse().getContentAsString(), TripDto.class);
		if(trip != null)
			tripId = trip.getId();
		LOG.info("Booking details : {}", trip);
	}
	
	@Test
	public void updateLocationTest() throws Exception{
		 onboardCabTest();
		UberCar car = uberData.getCabById(cabId);
		Location location = car.getCurrentLocation();
		LOG.info("Current location  {} " ,location);
		UpdateLocationRequest request  = new UpdateLocationRequest(car.getCabId(),new Coordinates(5L, 5L));
		 mockMvc.perform(put("/public/rest/uber/updateLocation").contentType(
	            MediaType.APPLICATION_JSON).content(gson.toJson(request))).andReturn();
		
	}
	
	@Test
	public void updateCabStatusTest() throws Exception{
		 onboardCabTest();
		UberCar car = uberData.getCabById(cabId);
		LOG.info("Car {}  current status {} ", car.getCabId(), car.getCabStatus());
		UpdateCabStatusRequest request = new UpdateCabStatusRequest(cabId, CabStatus.UNAVAILABLE);
		mockMvc.perform(put("/public/rest/uber/updateCabStatus").contentType(
	            MediaType.APPLICATION_JSON).content(gson.toJson(request))).andReturn();
		
	}
	
	@Test
	public void cancelCabTest() throws Exception {
		bookCabTest();
		if(tripId != null){
			CancelBookingRequest request = new CancelBookingRequest(tripId, true, "Driver not reachable");
			mockMvc.perform(put("/public/rest/uber/cancelCab").contentType(
	            MediaType.APPLICATION_JSON).content(gson.toJson(request))).andReturn();
		}
	}
	
	@Test
	public void completeTripTest() throws Exception {
		bookCabTest();
		CompleteTripRequest request = new CompleteTripRequest();
		request.setTripId(tripId);
		if(tripId != null)
			mockMvc.perform(put("/public/rest/uber/completeTrip").contentType(
	            MediaType.APPLICATION_JSON).content(gson.toJson(request))).andReturn();
	}
	
}
