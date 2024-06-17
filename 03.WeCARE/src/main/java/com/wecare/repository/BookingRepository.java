package com.wecare.repository;

import com.wecare.entity.Booking;
import com.wecare.entity.Coach;
import com.wecare.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
   public List<Booking> findByUser(User user);
   public List<Booking> findByCoach(Coach coach);
   public List<Booking> findByBookingId(Integer bookingId);
   public void deleteByBookingId(Integer bookingId);
   public boolean existsByUserAndAppointmentDateAndSlot(User user, LocalDate appointmentDate, String slot);

}
