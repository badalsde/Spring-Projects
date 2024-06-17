package com.courier.entity;

import com.courier.dto.CourierStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    private Integer weight;
    private LocalDate bookingDate;
    private String source;
    private String destination;
    private String priority;
    private Float bookingAmount;
    @Enumerated(EnumType.STRING)
    private CourierStatus status;
}
