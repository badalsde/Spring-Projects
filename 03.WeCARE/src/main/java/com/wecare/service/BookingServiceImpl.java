package com.wecare.service;

import com.wecare.dto.BookingDTO;
import com.wecare.entity.Booking;
import com.wecare.entity.Coach;
import com.wecare.entity.User;
import com.wecare.exception.WeCareException;
import com.wecare.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService{
    @Autowired
    private Environment environment;
    @Autowired
    private BookingRepository bookingRepository;


    @Override
    public Boolean bookAppointment(User user, Coach coach, LocalDate appointmentDate, String slot) throws WeCareException {
        if(bookingRepository.existsByUserAndAppointmentDateAndSlot(user, appointmentDate, slot)){
            throw new WeCareException(environment.getProperty("Service.BOOKING_ALREADY_EXISTS"));
        }
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setCoach(coach);
        booking.setAppointmentDate(appointmentDate);
        booking.setSlot(slot);
        bookingRepository.save(booking);

        return true;
    }

    @Override
    public Boolean rescheduleAppointment(Integer bookingId, LocalDate newAppointmentDate,String newSlot) throws WeCareException {
        if(bookingRepository.findByBookingId(bookingId)!=null){
            throw new WeCareException(environment.getProperty("Service.BOOKING_ALREADY_EXISTS"));
        }
        Booking booking = new Booking();
        booking.setAppointmentDate(newAppointmentDate);
        booking.setSlot(newSlot);
        bookingRepository.save(booking);
        return true;
    }

    @Override
    public void cancelAppointment(Integer bookingId) throws WeCareException {
        Booking booking = (Booking) bookingRepository.findByBookingId(bookingId);
        if(booking == null){
            throw new WeCareException(environment.getProperty("Service.BOOKING_NOT_FOUND"));
        }
        bookingRepository.deleteByBookingId(bookingId);
    }
}
