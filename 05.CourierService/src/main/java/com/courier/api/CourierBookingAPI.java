package com.courier.api;

import com.courier.dto.BookingDTO;
import com.courier.exception.CourierException;
import com.courier.service.BookingServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courier")
@Validated
public class CourierBookingAPI {
    @Autowired
    private Environment environment;
    @Autowired
    private BookingServiceImpl bookingService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> bookCourier(@Valid @RequestBody BookingDTO bookingDTO) throws CourierException {
        Integer bookingId = bookingService.bookCourier(bookingDTO);
        String successMessage = environment.getProperty("API.BOOKING_SUCCESS")+": "+bookingId;
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{bookingId}",produces = "application/json")
    public ResponseEntity<BookingDTO> getBookingDetails(@PathVariable("bookingId") @Pattern(regexp = "^\\d{4}$",message = "Booking ID must be a 4-digit number") String bookingId) throws CourierException {
        BookingDTO bookingDTO = bookingService.getBookingDetails(Integer.valueOf(bookingId));
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }
}
