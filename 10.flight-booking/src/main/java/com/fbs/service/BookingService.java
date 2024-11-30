package com.fbs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.dto.BookingDTO;
import com.fbs.dto.BookingResponse;
import com.fbs.dto.PassengerDTO;
import com.fbs.dto.PassengerResponse;
import com.fbs.entity.Booking;
import com.fbs.entity.Passenger;
import com.fbs.exception.BookingException;
import com.fbs.exception.FlightException;
import com.fbs.repository.BookingRepository;
import com.fbs.repository.PassengerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightService flightService;
    
    public String createBooking(BookingDTO bookingDTO) {
        // Generate a random UUID and PNR
        UUID uuid = UUID.randomUUID();
        String pnr = uuid.toString().replaceAll("[^0-9]", "").substring(0, 10);

        // Convert DTO to entity
        Booking booking = new Booking();
        booking.setPnrNumber(pnr);
        booking.setAirlines(bookingDTO.getAirlines());
        booking.setFlightId(bookingDTO.getFlightId());
        booking.setSource(bookingDTO.getSource());
        booking.setDestination(bookingDTO.getDestination());
        booking.setFlightAvailableDate(bookingDTO.getFlightAvailableDate());
        booking.setDepartureTime(bookingDTO.getDepartureTime());
        booking.setBookingDate(LocalDate.now());
        booking.setNoOfSeat(bookingDTO.getNoOfSeat());
        booking.setTotalFare(bookingDTO.getTotalFare());
        booking.setUserId(bookingDTO.getUserId());

        List<Passenger> passengers = new ArrayList<>();
        for (PassengerDTO passengerDTO : bookingDTO.getPassengers()) {
            Passenger passenger = new Passenger();
            passenger.setPassengerName(passengerDTO.getPassengerName());
            passenger.setPassengerAge(passengerDTO.getPassengerAge());
            passenger.setPassengerGender(passengerDTO.getPassengerGender());

            // Add passenger to booking (this will set the pnrNumber in Passenger)
            passenger.setBooking(booking);
            passengers.add(passenger);
        }
        // Save passengers first (so they have the booking set)
        passengerRepository.saveAll(passengers);

        // Save the booking
        bookingRepository.save(booking);

        int seatCount = Integer.parseInt(bookingDTO.getNoOfSeat());
        try {
            flightService.updateFlightSeat(bookingDTO.getFlightId(), seatCount);
        } catch (FlightException e) {
            e.printStackTrace();
        }
        
        return pnr;
    }
    
    public BookingResponse bookingDetailsByPNR(String pnr) throws BookingException {
        Booking booking = bookingRepository.findByPnrNumber(pnr);
        
        if (Objects.isNull(booking)) {
            throw new BookingException("Booking is not available: " + pnr);
        }
        BookingResponse response = new BookingResponse();
        
        response.setPnrNumber(booking.getPnrNumber());
        response.setFlightId(booking.getFlightId());
        response.setAirlines(booking.getAirlines());
        response.setBookingDate(booking.getBookingDate());
        response.setDepartureTime(booking.getDepartureTime());
        response.setSource(booking.getSource());
        response.setDestination(booking.getDestination());
        response.setFlightAvailableDate(booking.getFlightAvailableDate());
        response.setNoOfSeat(booking.getNoOfSeat());
        response.setTotalFare(booking.getTotalFare());
        System.out.println(booking.getPassengers());
        // Map the list of passengers
        List<PassengerResponse> passengerResponses = booking.getPassengers().stream()
                .map(passenger -> new PassengerResponse(
                        passenger.getPassengerId(),
                        passenger.getPassengerName(),
                        passenger.getPassengerAge(),
                        passenger.getPassengerGender()))
                .collect(Collectors.toList());
        
        response.setPassengers(passengerResponses);

        return response;
    }
    
    
    public List<BookingResponse> getBookingsByUserId(String userId) throws BookingException {
        List<Booking> bookings = bookingRepository.findByUserId(userId);

        if (bookings == null || bookings.isEmpty()) {
            throw new BookingException("No bookings found for User ID: " + userId);
        }

        return bookings.stream()
                .map(this::mapToBookingResponse)
                .collect(Collectors.toList());
    }
    
 // Helper method to map Booking entity to BookingResponse DTO
    private BookingResponse mapToBookingResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setPnrNumber(booking.getPnrNumber());
        response.setFlightId(booking.getFlightId());
        response.setAirlines(booking.getAirlines());
        response.setBookingDate(booking.getBookingDate());
        response.setDepartureTime(booking.getDepartureTime());
        response.setSource(booking.getSource());
        response.setDestination(booking.getDestination());
        response.setFlightAvailableDate(booking.getFlightAvailableDate());
        response.setNoOfSeat(booking.getNoOfSeat());
        response.setTotalFare(booking.getTotalFare());

        // Map passengers
        List<Passenger> passengers = booking.getPassengers();
        response.setPassengers(passengers.stream()
                .map(passenger -> new PassengerResponse(passenger.getPassengerId(), passenger.getPassengerName(), passenger.getPassengerAge(), passenger.getPassengerGender()))
                .collect(Collectors.toList()));

        return response;
    }
    
    
}
