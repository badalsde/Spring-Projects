package com.fbs.dto;

import java.time.LocalDate;
import java.util.List;

public class BookingResponse {
	private int id;
    private String pnrNumber;
    private String flightId;
    private String airlines;
    private String source;
    private String destination;
    private String flightAvailableDate;
    private String departureTime;
    private String noOfSeat;
    private String totalFare;
    private String userId;
    private LocalDate bookingDate;
    private List<PassengerResponse> passengers;
	
    public BookingResponse() {
		super();
	}

	public BookingResponse(int id, String pnrNumber, String flightId, String airlines, String source,
			String destination, String flightAvailableDate, String departureTime, String noOfSeat, String totalFare,
			String userId, LocalDate bookingDate, List<PassengerResponse> passengers) {
		super();
		this.id = id;
		this.pnrNumber = pnrNumber;
		this.flightId = flightId;
		this.airlines = airlines;
		this.source = source;
		this.destination = destination;
		this.flightAvailableDate = flightAvailableDate;
		this.departureTime = departureTime;
		this.noOfSeat = noOfSeat;
		this.totalFare = totalFare;
		this.userId = userId;
		this.bookingDate = bookingDate;
		this.passengers = passengers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getAirlines() {
		return airlines;
	}

	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlightAvailableDate() {
		return flightAvailableDate;
	}

	public void setFlightAvailableDate(String flightAvailableDate) {
		this.flightAvailableDate = flightAvailableDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getNoOfSeat() {
		return noOfSeat;
	}

	public void setNoOfSeat(String noOfSeat) {
		this.noOfSeat = noOfSeat;
	}

	public String getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(String totalFare) {
		this.totalFare = totalFare;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public List<PassengerResponse> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerResponse> passengers) {
		this.passengers = passengers;
	}

}
