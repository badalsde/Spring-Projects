package com.flight.api;

import com.flight.dto.BookingDTO;
import com.flight.dto.CreditcardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookingController {

    @GetMapping("/{FlightId}/{UserId}")
    public ResponseEntity<List<BookingDTO>> getBookingDetails(@PathVariable("FlightId") String FlightId, @PathVariable("UserId") String UserId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<Boolean> bookFlight(@RequestBody CreditcardDTO creditcardDTO) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
