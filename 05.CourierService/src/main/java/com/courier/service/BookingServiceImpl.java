package com.courier.service;

import com.courier.dto.BookingDTO;
import com.courier.dto.CourierStatus;
import com.courier.entity.Booking;
import com.courier.exception.CourierException;
import com.courier.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService{

    @Autowired
    private Environment environment;
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Integer bookCourier(BookingDTO bookingDTO) throws CourierException {
        float bookingAmount=calculateBookingAmount(bookingDTO.getWeight(), bookingDTO.getPriority());
        bookingDTO.setBookingAmount(bookingAmount);
        bookingDTO.setStatus(CourierStatus.BOOKED);

        Booking booking = new Booking();
        booking.setWeight(bookingDTO.getWeight());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setSource(bookingDTO.getSource());
        booking.setDestination(bookingDTO.getDestination());
        booking.setPriority(bookingDTO.getPriority());
        booking.setBookingAmount(bookingDTO.getBookingAmount());
        booking.setStatus(bookingDTO.getStatus());
        bookingRepository.save(booking);
        return booking.getBookingId();
    }
    @Override
    public Float calculateBookingAmount(Integer weight, String priority) throws CourierException {
        float bookingAmount = 0f;
        if(weight < 100){
            bookingAmount = 200f;
        }else if(weight < 300){
            bookingAmount = 1000f;
        } else if(weight < 1000){
            float extraWeightCharge=(weight - 300f) *2;
            bookingAmount = 300f + extraWeightCharge;
        }else{
            bookingAmount = 1500f;
        }

        if(priority.equalsIgnoreCase("high")){
            bookingAmount += 100f;
        } else if(priority.equalsIgnoreCase("medium")){
            bookingAmount += 60f;
        }

        return bookingAmount;
    }

    @Override
    public BookingDTO getBookingDetails(Integer bookingId) throws CourierException {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        Booking booking = bookingOptional.orElseThrow(()-> new CourierException("Service.NO_RECORDS_FOUND"));
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setWeight(booking.getWeight());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setSource(booking.getSource());
        bookingDTO.setDestination(booking.getDestination());
        bookingDTO.setPriority(booking.getPriority());
        bookingDTO.setBookingAmount(booking.getBookingAmount());
        bookingDTO.setStatus(booking.getStatus());
        return bookingDTO;
    }


}
