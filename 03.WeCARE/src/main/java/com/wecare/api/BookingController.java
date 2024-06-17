package com.wecare.api;

import com.wecare.dto.AppointmentRequest;
import com.wecare.dto.BookingDTO;
import com.wecare.entity.Coach;
import com.wecare.entity.User;
import com.wecare.exception.WeCareException;
import com.wecare.service.BookingServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private Environment environment;
    @Autowired
    private BookingServiceImpl bookingService;

    @PostMapping(value = "/users/{userId}/booking/{coachId}", consumes = "application/json")
    public ResponseEntity<Boolean> bookAppointment(@PathVariable("userId") String userId, @PathVariable("coachId") String coachId, @RequestBody AppointmentRequest appointmentRequest) throws WeCareException {
        User user= new User();
        user.setUserId(userId);
        Coach coach= new Coach();
        coach.setCoachId(coachId);
        Boolean response = bookingService.bookAppointment(user,coach,appointmentRequest.getAppointmentDate(),appointmentRequest.getSlot());
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping(value = "/booking/{bookingId}")
    public ResponseEntity<Boolean> rescheduleAppointment(@PathVariable("bookingId") Integer bookingId, @RequestBody AppointmentRequest appointmentRequest) throws WeCareException {
        Boolean response=bookingService.rescheduleAppointment(bookingId, appointmentRequest.getAppointmentDate(), appointmentRequest.getSlot());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping(value = "/booking/{bookingId}")
    public ResponseEntity<?> cancelAppointment(@PathVariable("bookingId") Integer bookingId) throws WeCareException {
        bookingService.cancelAppointment(bookingId);
        String msg = environment.getProperty("API.DELETE_SUCCESS");
        return new ResponseEntity<>(msg,HttpStatus.OK);
    }
}
