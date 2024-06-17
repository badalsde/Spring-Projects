package com.flight.api;

import com.flight.dto.FlightDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flight.utility.CalendarUtility;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @GetMapping(value = "/{source}")
    public ResponseEntity<List<FlightDTO>> getSourceCityDetails(@PathVariable("source") String source) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{destination}")
    public ResponseEntity<List<FlightDTO>> getDestinationCityDetails(@PathVariable("destination") String destination) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{source}/{destination}/{journeyDate")
    public ResponseEntity<List<FlightDTO>> getFlightDetails(@PathVariable("source") String source, @PathVariable("destination") String destination, @PathVariable("journeyDate") String journeyDate) throws Exception {
        Calendar jDate = CalendarUtility.getCalendarFromString(journeyDate);


        return new ResponseEntity<>(HttpStatus.OK);
    }

}
