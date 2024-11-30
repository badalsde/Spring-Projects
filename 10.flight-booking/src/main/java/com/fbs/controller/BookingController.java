package com.fbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.dto.BookingDTO;
import com.fbs.dto.BookingResponse;
import com.fbs.exception.BookingException;
import com.fbs.service.BookingService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @PostMapping
    public ResponseEntity<String> createBooking(@RequestBody BookingDTO bookingDTO) {
      String pnr = bookingService.createBooking(bookingDTO);
      return new ResponseEntity<>(pnr,HttpStatus.OK); 
    }
    
    @GetMapping(value="/{pnr}")
    public ResponseEntity<BookingResponse> getBookingByPnr(@PathVariable String pnr) throws BookingException {
        BookingResponse response = bookingService.bookingDetailsByPNR(pnr);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping(value="/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingByUserId(@PathVariable String userId) throws BookingException {
        List<BookingResponse> bookings = bookingService.getBookingsByUserId(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    
}
