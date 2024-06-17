package com.cabbooking.service;

import com.cabbooking.dto.BookingDTO;
import com.cabbooking.dto.CabDTO;
import com.cabbooking.entity.Booking;
import com.cabbooking.entity.Cab;
import com.cabbooking.exception.CabBookingException;
import com.cabbooking.repository.BookingRepository;
import com.cabbooking.repository.CabRepository;
import com.cabbooking.validator.BookingValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("cabBookingService")
@Transactional
public class CabBookingServiceImpl implements CabBookingService{

    @Autowired
    private BookingValidator bookingValidator;
    @Autowired
    private CabRepository cabRepository;;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private Environment environment;

    @Override
    public List<BookingDTO> getDetailsByBookingType(String bookingType) throws CabBookingException {
        List<Booking> bookingList = bookingRepository.findByBookingType(bookingType);
        if(bookingList.isEmpty()){
            throw new CabBookingException(environment.getProperty("Service.NO_DETAILS_FOUND"));
        }
        List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
        for(Booking booking:bookingList){
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setBookingId(booking.getBookingId());
            bookingDTO.setCustomerName(booking.getCustomerName());
            bookingDTO.setPhoneNo(booking.getPhoneNo());
            bookingDTO.setBookingType(booking.getBookingType());
            if(booking.getCab()!=null) {
                CabDTO cabDTO = new CabDTO();
                cabDTO.setCabNo(booking.getCab().getCabNo());
                cabDTO.setModelName(booking.getCab().getModelName());
                cabDTO.setDriverPhoneNo(booking.getCab().getDriverPhoneNo());
                cabDTO.setAvailability(booking.getCab().getAvailability());
                bookingDTO.setCabDTO(cabDTO);
            }
            bookingDTOs.add(bookingDTO);
        }
        return bookingDTOs;
    }

    @Override
    public Integer bookCab(BookingDTO bookingDTO) throws CabBookingException {
        // Validate BookingDTO
       bookingValidator.validate(bookingDTO);

        if(bookingDTO.getCabDTO()==null){
            throw new CabBookingException(environment.getProperty("Service.CAB_NOT_FOUND"));
        }

        // Get Cab details
        Integer cabNo = bookingDTO.getCabDTO().getCabNo();
        Cab cab = cabRepository.findByCabNo(cabNo);

        if(cab==null){
            throw new CabBookingException(environment.getProperty("Service.CAB_NOT_FOUND"));
        }
        // Check cab availability
        if("No".equalsIgnoreCase(cab.getAvailability())){
            throw new CabBookingException(environment.getProperty("Service.CAB_NOT_AVAILABLE"));
        }
        // Book the cab
        Booking booking = new Booking();
        booking.setCustomerName(bookingDTO.getCustomerName());
        booking.setPhoneNo(bookingDTO.getPhoneNo());
        booking.setBookingType(bookingDTO.getBookingType());
        booking.setCab(cab);
        // Set cab availability to "No"
        cab.setAvailability("No");
        // Save booking to database
        Booking savedBooking = bookingRepository.save(booking);
        return savedBooking.getBookingId();
    }
}
