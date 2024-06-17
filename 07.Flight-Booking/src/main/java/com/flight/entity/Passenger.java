package com.flight.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passengerId;
    private String passengerName;
    private Integer passengerAge;
    private String passengerGender;
    @ManyToOne
    @JoinColumn(name="ticket_pnr")
    private Ticket ticket;
}
