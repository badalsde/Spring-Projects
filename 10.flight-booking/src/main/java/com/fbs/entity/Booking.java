package com.fbs.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
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

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Passenger> passengers;
    
	public Booking() {
		super();
	}

	public Booking(int id, String pnrNumber, String flightId, String airlines, String source, String destination,
			String flightAvailableDate, String departureTime, String noOfSeat, String totalFare, String userId,
			LocalDate bookingDate, List<Passenger> passengers) {
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

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

}
