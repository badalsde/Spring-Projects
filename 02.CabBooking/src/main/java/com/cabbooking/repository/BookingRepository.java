package com.cabbooking.repository;

import com.cabbooking.entity.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking,Integer>{

    public List<Booking> findByBookingType(String bookingType);
}
