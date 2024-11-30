package com.fbs.entity;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="flight_details")
public class Flight {
    @Id
    @Column(length = 10, nullable = false)
    private String flightId;
    @Column(length = 50, nullable = false)
    private String airlines;
    @Column(length = 50, nullable = false)
    private String source;
    @Column(length = 50, nullable = false)
    private String destination;
    @Column(length = 10, nullable = false)
    private Double fare;
    @DateTimeFormat(pattern = "dd-MM-yyyy")  
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate flightAvailableDate;
    
    @Column(nullable = false)
    private String departureTime;
    @Column( nullable = false)
    private String arrivalTime;
    @Column(length = 3, nullable = false)
    private Integer seatCount;
	
    public Flight() {
		super();
	}

	public Flight(String flightId, String airlines, String source, String destination, Double fare,
			LocalDate flightAvailableDate, String departureTime, String arrivalTime, Integer seatCount) {
		super();
		this.flightId = flightId;
		this.airlines = airlines;
		this.source = source;
		this.destination = destination;
		this.fare = fare;
		this.flightAvailableDate = flightAvailableDate;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.seatCount = seatCount;
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

	public Double getFare() {
		return fare;
	}

	public void setFare(Double fare) {
		this.fare = fare;
	}

	public LocalDate getFlightAvailableDate() {
		return flightAvailableDate;
	}

	public void setFlightAvailableDate(LocalDate flightAvailableDate) {
		this.flightAvailableDate = flightAvailableDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Integer getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", airlines=" + airlines + ", source=" + source + ", destination="
				+ destination + ", fare=" + fare + ", flightAvailableDate=" + flightAvailableDate + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", seatCount=" + seatCount + "]";
	}
}
