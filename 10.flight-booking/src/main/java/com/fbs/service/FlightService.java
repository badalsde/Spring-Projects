package com.fbs.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.entity.Flight;
import com.fbs.exception.FlightException;
import com.fbs.repository.FlightRepository;

import jakarta.transaction.Transactional;

@Service(value="flightService")
@Transactional
public class FlightService {
	
	private static final Logger logger = LogManager.getLogger(FlightService.class);
	
	@Autowired
	private FlightRepository flightRepository;
	
	public List<Flight> searchFlight(String source,String destination, LocalDate date) throws FlightException{
		List<Flight> flights = flightRepository.findFlightDetails(source, destination, date);
		if (flights == null || flights.isEmpty()) {
            logger.error("No flights available for source: {}, destination: {} and date: {}", source, destination, date);
            throw new FlightException("No Flight available");
        }
		return flights;
	}
	
	public Flight getFlight(String flightId) throws FlightException{
		Flight flight = flightRepository.findFlight(flightId);
		if (Objects.isNull(flight)) {
            logger.error("No flights available for this FlightId: {}", flightId);
            throw new FlightException("Flight is not available");
        }
		return flight;
	}
	
	public String updateFlightSeat(String flightId, Integer noOfSeats) throws FlightException{
		if (noOfSeats <= 0) {
            throw new FlightException("The number of seats to be deducted must be greater than 0.");
        }
		
		Flight flight = getFlight(flightId);

		if (Objects.isNull(flight)) {
            logger.error("No flights available for this FlightId: {}", flightId);
            throw new FlightException("Flight is not available");
        }
		
		flightRepository.updateSeatsDetails(flightId, noOfSeats);
		
		return "Flight Seat update successfully";
	}
	
	public List<String> findSource() throws FlightException{
		List<String> sources = flightRepository.findFlightSources();
		if (sources == null || sources.isEmpty()) {
            logger.error("No flight Source available");
            throw new FlightException("No flights Source available");
        }
		return sources;
	}
	
	public List<String> findDestination() throws FlightException{
		List<String> destinations = flightRepository.findFlightDestinations();
		if (destinations == null || destinations.isEmpty()) {
            logger.error("No flight Destination available");
            throw new FlightException("No flights Destination available");
        }
		return destinations;
	}
	
}
