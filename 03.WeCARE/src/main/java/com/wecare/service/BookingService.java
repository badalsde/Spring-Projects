package com.wecare.service;

import com.wecare.dto.BookingDTO;
import com.wecare.entity.Coach;
import com.wecare.entity.User;
import com.wecare.exception.WeCareException;

import java.time.LocalDate;

public interface BookingService {

    public Boolean bookAppointment(User user, Coach coach, LocalDate appointmentDate, String slot) throws WeCareException;
    public Boolean rescheduleAppointment(Integer bookingId, LocalDate newAppointmentDate,String newSlot) throws WeCareException;
    public void cancelAppointment(Integer bookingId) throws WeCareException;
}
