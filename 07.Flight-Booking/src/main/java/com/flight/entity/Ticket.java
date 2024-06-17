package com.flight.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @Column(name = "ticket_pnr")
    private String ticketPnr;
    private String bookingDate;
    private String departureDate;
    private String departureTime;
    private String totalFare;
    @ManyToOne
    @JoinColumn(name="flight_id")
    private Flight flight;
    @JoinColumn(name="user_id")
    @OneToOne(cascade = CascadeType.REFRESH)
    private User user;
    private int noOfSeats;
}
