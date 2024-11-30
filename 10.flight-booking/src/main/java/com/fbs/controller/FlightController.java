package com.fbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.entity.Flight;
import com.fbs.exception.FlightException;
import com.fbs.service.FlightService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/flight")
@Validated
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@PostMapping(value="/search", produces="application/json")
	public ResponseEntity<List<Flight>> searchFlight(@Valid @RequestBody Flight flight) throws FlightException{
		List<Flight> flights = flightService.searchFlight(flight.getSource(), flight.getDestination(), flight.getFlightAvailableDate());
		return new ResponseEntity<>(flights,HttpStatus.OK);
	}
	
	@GetMapping(value="/{flightId}")
	public ResponseEntity<Flight> getFlight(@PathVariable String flightId) throws FlightException{
		Flight flight = flightService.getFlight(flightId);
		return new ResponseEntity<>(flight,HttpStatus.OK);
	}
	
	@GetMapping(value="/{flightId}/{noOfSeat}")
	public ResponseEntity<String> updateFlightSeat(@PathVariable String flightId, @PathVariable Integer noOfSeat) throws FlightException{
		String msg = flightService.updateFlightSeat(flightId, noOfSeat);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping(value="/source")
	public ResponseEntity<List<String>> getSource() throws FlightException{
		List<String> sources= flightService.findSource();
		return new ResponseEntity<>(sources,HttpStatus.OK);
	}
	
	@GetMapping(value="/destination")
	public ResponseEntity<List<String>> getDestination() throws FlightException{
		List<String> destinations= flightService.findDestination();
		return new ResponseEntity<>(destinations,HttpStatus.OK);
	}
}
