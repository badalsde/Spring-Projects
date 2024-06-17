package com.flight.dto;

import com.flight.entity.Flight;
import com.flight.entity.Ticket;
import com.flight.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class BookingDTO {

    private int passengerId;
    private String passengerName;
    private Integer passengerAge;
    private String passengerGender;
    private Ticket ticket;

    private String ticketPnr;
    private String bookingDate;
    private String departureDate;
    private String departureTime;
    private String totalFare;
    private Flight flight;
    private User user;
    private int noOfSeats;

}
