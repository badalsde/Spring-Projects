package com.wecare.service;

import com.wecare.dto.BookingDTO;
import com.wecare.dto.UserDTO;
import com.wecare.exception.WeCareException;
import java.util.List;


public interface UserService {
    public void createUser(UserDTO userDTO) throws WeCareException;
    public Boolean userLogin(String userId, String password) throws WeCareException;
    public UserDTO getUserDetails(String userId) throws WeCareException;
    public List<BookingDTO> getMyAppointments(String userId) throws WeCareException;
}
