package com.castleglobal.uber.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.castleglobal.uber.client.request.BookCabRequest;
import com.castleglobal.uber.client.request.CancelBookingRequest;
import com.castleglobal.uber.client.request.CompleteTripRequest;
import com.castleglobal.uber.client.request.OnboardCabRequest;
import com.castleglobal.uber.client.request.OnboardRiderRequest;
import com.castleglobal.uber.client.request.ShowPastTripsRequest;
import com.castleglobal.uber.client.request.UpdateCabStatusRequest;
import com.castleglobal.uber.client.request.UpdateLocationRequest;
import com.castleglobal.uber.model.Trip;
import com.castleglobal.uber.services.IUberServices;

@RestController
@RequestMapping("/public/rest/uber")
public class UberRestService {

	@Autowired
	private IUberServices uberService;

	private final static Logger LOG = LoggerFactory.getLogger(UberRestService.class);

	@RequestMapping(value = "onboardCab", method = RequestMethod.POST)
	public String onboardCab(@RequestBody OnboardCabRequest request) {
		LOG.info("Onboarding  request received  {} ", request);
		String cabCode = uberService.onboardCab(request.getCarPlateNumber(), request.getCurrentLocation(),
				request.getCabStatus(), request.getCabType(), request.getDriver());
		LOG.info("Onboarded cab  {} " , cabCode);
		return cabCode;
	}

	@RequestMapping(value = "onboardRider", method = RequestMethod.POST)
	public String onboardRider(@RequestBody OnboardRiderRequest request) {
		LOG.info("Onboarding request received {} " , request);
		String riderCode = uberService.onboardRider(request.getRiderLocation(), request.getName(), request.getMobile());
		return riderCode;
	}

	@RequestMapping(value = "bookCab", method = RequestMethod.POST)
	public Trip requestCabByTypeAndLocation(@RequestBody BookCabRequest request) {
		LOG.info("Received request to book a cab {} " , request );
		return uberService.requestCabByTypeAndLocation(request.getCabType(), request.getRiderId(),
				request.getPickup(), request.getDrop());
	}

	@RequestMapping(value = "updateLocation", method = RequestMethod.PUT)
	public void updateLocation(@RequestBody UpdateLocationRequest request) {
		LOG.info(" updateLocationRequest {} ", request);
		uberService.updateLocation(request.getCabId(), request.getXy());
	}

	@RequestMapping(value = "updateCabStatus", method = RequestMethod.PUT)
	public void updateCabStatus(@RequestBody UpdateCabStatusRequest request) {
		LOG.info(" UpdateCabStatus {} ", request);
		uberService.updateCabStatus(request.getCabId(), request.getCabStatus());
	}

	@RequestMapping(value = "cancelCab", method = RequestMethod.PUT)
	public void cancelBooking(@RequestBody CancelBookingRequest request) {
		LOG.info("CancelBookingRequest received {} ", request);
		uberService.cancelBooking(request.getTripId(), request.isIntiatedByRider(), request.getComments());
	}

	@RequestMapping(value = "completeTrip", method = RequestMethod.PUT)
	public void completeTrip(@RequestBody CompleteTripRequest request) {
		LOG.info("CompleteTrip Request {} " , request);
		uberService.completeTrip(request.getTripId());
	}

	@RequestMapping(value = "showTripHistory")
	public void showPastTrips(@RequestBody ShowPastTripsRequest request) {
		LOG.info("show Past Trips {} ", request);
		uberService.showPastTrips(request.getId(), request.isInitiatedByRider());
	}
}
