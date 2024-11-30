package com.fbs.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class FlightDTO {
	@NotNull(message = "Flight ID cannot be null")
    @Size(min = 3, max = 10, message = "Flight ID must be between 3 and 10 characters")
    private String flightId;

    @NotNull(message = "Airline cannot be null")
    @Size(min = 3, max = 50, message = "Airline name must be between 3 and 50 characters")
    private String airlines;

    @NotNull(message = "Source cannot be null")
    @Size(min = 3, max = 50, message = "Source must be between 3 and 50 characters")
    private String source;

    @NotNull(message = "Destination cannot be null")
    @Size(min = 3, max = 50, message = "Destination must be between 3 and 50 characters")
    private String destination;

    @NotNull(message = "Fare cannot be null")
    @Min(value = 1, message = "Fare must be greater than 0")
    private Double fare;

    @NotNull(message = "Flight available date cannot be null")
    @PastOrPresent(message = "Flight available date cannot be in the future")
    private LocalDate flightAvailableDate;

    @NotNull(message = "Departure time cannot be null")
    @Pattern(regexp = "^[0-9]{2}:[0-9]{2}$", message = "Departure time must be in HH:mm format")
    private String departureTime;

    @NotNull(message = "Arrival time cannot be null")
    @Pattern(regexp = "^[0-9]{2}:[0-9]{2}$", message = "Arrival time must be in HH:mm format")
    private String arrivalTime;

    @NotNull(message = "Seat count cannot be null")
    @Min(value = 1, message = "Seat count must be greater than 0")
    private Integer seatCount;
    
	public FlightDTO() {
		super();
	}

	public FlightDTO(String flightId, String airlines, String source, String destination, Double fare,
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
		return "FlightDTO [flightId=" + flightId + ", airlines=" + airlines + ", source=" + source + ", destination="
				+ destination + ", fare=" + fare + ", flightAvailableDate=" + flightAvailableDate + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", seatCount=" + seatCount + "]";
	}
 
}
