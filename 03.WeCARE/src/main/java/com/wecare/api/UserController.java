package com.wecare.api;

import com.wecare.dto.BookingDTO;
import com.wecare.dto.UserDTO;
import com.wecare.entity.User;
import com.wecare.exception.WeCareException;
import com.wecare.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private Environment environment;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) throws WeCareException {
        userService.createUser(userDTO);
        String successMessage = environment.getProperty("API.CREATION_SUCCESS")+": "+userDTO.getUserId();
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }


    @PostMapping(value = "/login",consumes = "application/json")
    public ResponseEntity<Boolean> userLogin(@Valid @RequestBody User user) throws WeCareException {
        Boolean loginSuccessful = userService.userLogin(user.getUserId(),user.getPassword());
        return new ResponseEntity<>(loginSuccessful?HttpStatus.OK:HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable("userId") String userId) throws WeCareException {
        UserDTO userDTO = userService.getUserDetails(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/booking/{userId}")
    public ResponseEntity<List<BookingDTO>> myAppointments(@PathVariable("userId") String userId) throws WeCareException{
        List<BookingDTO> bookingDTOList = userService.getMyAppointments(userId);
        return new ResponseEntity<>(bookingDTOList, HttpStatus.OK);
    }
}