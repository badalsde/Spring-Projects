package com.cabbooking.api;

import com.cabbooking.dto.BookingDTO;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.service.CabBookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CabBookingAPI {
    @Autowired
    private Environment environment;;
    @Autowired
    private CabBookingServiceImpl cabBookingService;

    @GetMapping(value = "cab/{bookingType}")
    public ResponseEntity<List<BookingDTO>> getDetailsByBookingType(@PathVariable("bookingType") String bookingType) throws CabBookingException {
        List<BookingDTO> bookings = cabBookingService.getDetailsByBookingType(bookingType);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping(value = "cab", consumes = "application/json")
    public ResponseEntity<String> bookCab(@RequestBody BookingDTO bookingDTO) throws CabBookingException {
        Integer bookingId = cabBookingService.bookCab(bookingDTO);
        String successMessage = environment.getProperty("API.BOOKING_SUCCESS")+": "+bookingId;
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }
}
