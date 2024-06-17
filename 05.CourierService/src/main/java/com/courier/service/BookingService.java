package com.courier.service;

import com.courier.exception.CourierException;
import com.courier.dto.BookingDTO;

public interface BookingService {
    public Integer bookCourier(BookingDTO bookingDTO) throws CourierException;

    public BookingDTO getBookingDetails(Integer bookingId) throws CourierException;

    public Float calculateBookingAmount(Integer weight, String priority) throws CourierException;
}