package com.wecare.service;

import com.wecare.dto.BookingDTO;
import com.wecare.dto.UserDTO;
import com.wecare.entity.Booking;
import com.wecare.entity.User;
import com.wecare.exception.WeCareException;
import com.wecare.repository.BookingRepository;
import com.wecare.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private Environment environment;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public void createUser(UserDTO userDTO) throws WeCareException {
        User userTest = userRepository.findByUserId(userDTO.getUserId());
        if(userTest!=null) {
            throw new WeCareException(environment.getProperty("Service.USER_ALREADY_EXISTS"));
        }
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setGender(userDTO.getGender());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setPassword(userDTO.getPassword());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setEmail(userDTO.getEmail());
        user.setPincode(userDTO.getPincode());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setCountry(userDTO.getCountry());
        userRepository.save(user);
    }

    @Override
    public Boolean userLogin(String userId, String password) throws WeCareException {
        User user = userRepository.findByUserId(userId);
        if(user==null) {
            throw new WeCareException(environment.getProperty("Service.USER_NOT_FOUND"));
        }
        if(!user.getPassword().equals(password)) {
            throw new WeCareException(environment.getProperty("Service.INVALID_PASSWORD"));
        }
        return true;
    }

    @Override
    public UserDTO getUserDetails(String userId) throws WeCareException {
        User userTest = userRepository.findByUserId(userId);
        if(userTest==null) {
            throw new WeCareException(environment.getProperty("Service.USER_NOT_FOUND"));
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userTest.getUserId());
        userDTO.setName(userTest.getName());
        userDTO.setGender(userTest.getGender());
        userDTO.setDateOfBirth(userTest.getDateOfBirth());
        userDTO.setPassword(userTest.getPassword());
        userDTO.setMobileNumber(userTest.getMobileNumber());
        userDTO.setEmail(userTest.getEmail());
        userDTO.setPincode(userTest.getPincode());
        userDTO.setCity(userTest.getCity());
        userDTO.setState(userTest.getState());
        userDTO.setCountry(userTest.getCountry());
        return userDTO;
    }

    @Override
    public List<BookingDTO> getMyAppointments(String userId) throws WeCareException {
        User user = userRepository.findByUserId(userId);
        if(user==null) {
            throw new WeCareException(environment.getProperty("Service.USER_NOT_FOUND"));
        }
        List<Booking> bookingList = bookingRepository.findByUser(user);
        if(bookingList.isEmpty()){
            throw new WeCareException(environment.getProperty("Service.NO_BOOKING_DETAILS_FOUND"));
        }
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for(Booking booking: bookingList){
            BookingDTO bookingDTO = new BookingDTO();
            bookingDTO.setBookingId(booking.getBookingId());
            bookingDTO.setUser(booking.getUser());
            bookingDTO.setCoach(booking.getCoach());
            bookingDTO.setAppointmentDate(booking.getAppointmentDate());
            bookingDTO.setSlot(booking.getSlot());
            bookingDTOs.add(bookingDTO);
        }
        return bookingDTOs;
    }
}
