package com.cabbooking.service;

import com.cabbooking.dto.BookingDTO;
import com.cabbooking.exception.CabBookingException;

import java.util.List;

public interface CabBookingService {
    public List<BookingDTO> getDetailsByBookingType(String bookingType) throws CabBookingException;
    public Integer bookCab(BookingDTO bookingDTO) throws CabBookingException;
}
