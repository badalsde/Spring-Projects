package com.cabbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer bookingId;
    private String customerName;
    private Long phoneNo;
    private String bookingType;
    @OneToOne
    @JoinColumn(name = "cab_no")
    private Cab cab;
}
