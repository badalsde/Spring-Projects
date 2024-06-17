package com.wecare.service;

import com.wecare.dto.BookingDTO;
import com.wecare.dto.CoachDTO;
import com.wecare.exception.WeCareException;

import java.util.List;

public interface CoachService {
    public void createCoach(CoachDTO coachDTO) throws WeCareException;
    public Boolean coachLogin(String coachId, String password) throws WeCareException;
    public CoachDTO getCoachDetails(String coachId) throws WeCareException;
    public List<CoachDTO> getAllCoaches() throws WeCareException;
    public List<BookingDTO> getBookingDetails(String coachId) throws WeCareException;
}
