package com.wecare.dto;

import com.wecare.entity.Coach;
import com.wecare.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private int bookingId;
    private User user;
    private Coach coach;
    private LocalDate appointmentDate;
    private String slot;
}
