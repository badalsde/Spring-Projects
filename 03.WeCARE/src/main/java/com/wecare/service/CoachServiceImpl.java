package com.wecare.service;

import com.wecare.dto.BookingDTO;
import com.wecare.dto.CoachDTO;
import com.wecare.entity.Booking;
import com.wecare.entity.Coach;
import com.wecare.exception.WeCareException;
import com.wecare.repository.BookingRepository;
import com.wecare.repository.CoachRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("coachService")
@Transactional
public class CoachServiceImpl implements CoachService{
    @Autowired
    private Environment environment;
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public void createCoach(CoachDTO coachDTO) throws WeCareException {
        Coach coachTest = coachRepository.findByCoachId(coachDTO.getCoachId());
        if(coachTest!=null){
            throw new WeCareException(environment.getProperty("Service.COACH_ALREADY_EXISTS"));
        }
        Coach coach = new Coach();
        coach.setCoachId(coachDTO.getCoachId());
        coach.setName(coachDTO.getName());
        coach.setGender(coachDTO.getGender());
        coach.setDateOfBirth(coachDTO.getDateOfBirth());
        coach.setPassword(coachDTO.getPassword());
        coach.setMobileNumber(coachDTO.getMobileNumber());
        coach.setSpeciality(coachDTO.getSpeciality());
        coachRepository.save(coach);
    }

    @Override
    public Boolean coachLogin(String coachId, String password) throws WeCareException {
        Coach coach = coachRepository.findByCoachId(coachId);
        if(coach==null) {
            throw new WeCareException(environment.getProperty("Service.COACH_NOT_FOUND"));
        }
        if(!coach.getPassword().equals(password)) {
            throw new WeCareException(environment.getProperty("Service.INVALID_PASSWORD"));
        }
        return true;
    }

    @Override
    public CoachDTO getCoachDetails(String coachId) throws WeCareException {
        Coach coachTest = coachRepository.findByCoachId(coachId);
        if(coachTest==null) {
            throw new WeCareException(environment.getProperty("Service.COACH_NOT_FOUND"));
        }
        CoachDTO coachDTO = new CoachDTO();
        coachDTO.setCoachId(coachTest.getCoachId());
        coachDTO.setName(coachTest.getName());
        coachDTO.setGender(coachTest.getGender());
        coachDTO.setDateOfBirth(coachTest.getDateOfBirth());
        coachDTO.setPassword(coachTest.getPassword());
        coachDTO.setMobileNumber(coachTest.getMobileNumber());
        coachDTO.setSpeciality(coachTest.getSpeciality());
        return coachDTO;
    }

    @Override
    public List<CoachDTO> getAllCoaches() throws WeCareException {
        List<Coach> coachList = (List<Coach>) coachRepository.findAll();
        if(coachList.isEmpty()){
            throw new WeCareException(environment.getProperty("Service.NO_COACH_FOUND"));
        }
        List<CoachDTO> coachDTOs = new ArrayList<>();
        for(Coach coach:coachList){
            CoachDTO coachDTO = new CoachDTO();
            coachDTO.setCoachId(coach.getCoachId());
            coachDTO.setName(coach.getName());
            coachDTO.setGender(coach.getGender());
            coachDTO.setDateOfBirth(coach.getDateOfBirth());
            coachDTO.setPassword(coach.getPassword());
            coachDTO.setMobileNumber(coach.getMobileNumber());
            coachDTO.setSpeciality(coach.getSpeciality());
            coachDTOs.add(coachDTO);
        }
        return coachDTOs;
    }

    @Override
    public List<BookingDTO> getBookingDetails(String coachId) throws WeCareException {
        Coach coach = coachRepository.findByCoachId(coachId);
        if(coach == null){
            throw new WeCareException(environment.getProperty("Service.COACH_NOT_FOUND"));
        }
        List<Booking> bookingList = bookingRepository.findByCoach(coach);
        if(bookingList.isEmpty()){
            throw new WeCareException(environment.getProperty("Service.NO_BOOKING_DETAILS_FOUND"));
        }
        List<BookingDTO> bookingDTOS = new ArrayList<BookingDTO>();
        for(Booking booking:bookingList){
            BookingDTO bookingDTO=new BookingDTO();
            bookingDTO.setBookingId(booking.getBookingId());
            bookingDTO.setUser(booking.getUser());
            bookingDTO.setCoach(booking.getCoach());
            bookingDTO.setAppointmentDate(booking.getAppointmentDate());
            bookingDTO.setSlot(booking.getSlot());
            bookingDTOS.add(bookingDTO);
        }
        return bookingDTOS;
    }
}
