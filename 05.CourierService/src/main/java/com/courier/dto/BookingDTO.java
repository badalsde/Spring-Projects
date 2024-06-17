package com.courier.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {

    @NotNull(message = "Booking ID cannot be null")
    @Digits(integer = 4, fraction = 0, message = "Booking ID must be a 4-digit number")
    private Integer bookingId;

    @NotNull(message = "Weight cannot be null")
    @Min(value = 30, message = "Please enter weight above 30 grams")
    private Integer weight;

    private LocalDate bookingDate;

    @NotNull(message = "source is mandatory")
    @NotEmpty(message = "source must not be empty")
    private String source;

    @NotNull(message = "destination is mandatory")
    @NotEmpty(message = "destination must not be empty")
    private String destination;

    @Pattern(regexp = "^(LOW|MEDIUM|HIGH)$", message = "Priority can either be LOW, MEDIUM, HIGH")
    private String priority;
    private Float bookingAmount;
    private CourierStatus status;
}
