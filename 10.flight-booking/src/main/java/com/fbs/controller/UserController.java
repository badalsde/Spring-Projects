package com.fbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.dto.LoginDTO;
import com.fbs.dto.UpdatePasswordDTO;
import com.fbs.dto.UserDTO;
import com.fbs.exception.UserException;
import com.fbs.service.UserService;

import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(produces="application/json")
	public ResponseEntity<String> registration(@Valid @RequestBody UserDTO userDTO) throws UserException{
		String id = userService.userRegistration(userDTO);
		String msg = "User is registed with userId: "+id;
		return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
	}
	
	@PostMapping( value="/login",produces="application/json")
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) throws UserException{
		
		String name = userService.userLogin(loginDTO.getUserId(), loginDTO.getPassword());
		String msg = "Welcome , "+name;
		return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable String userId) throws UserException{
		UserDTO userDTO = userService.getUserDetails(userId);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> getAllUser() throws UserException{
		List<UserDTO> userDTOs = userService.getAllUserDetails();
		return new ResponseEntity<>(userDTOs, HttpStatus.OK);
	}
	
	@PostMapping(value="/forgot-password", produces="application/json")
	public ResponseEntity<String> updatePassword(@Valid @RequestBody UpdatePasswordDTO update) throws UserException{
		userService.updatePassword(update.getEmail(),update.getPhone(),update.getNewPassword());
		String msg = "Password Updated Successfully";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
}
