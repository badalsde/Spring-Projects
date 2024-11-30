package com.fbs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.dto.UserDTO;
import com.fbs.entity.User;
import com.fbs.exception.UserException;
import com.fbs.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service(value="userService")
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	private static final Logger logger = LogManager.getLogger();
	
	public String userRegistration(UserDTO userDTO) throws UserException{
		User userCheck = userRepository.findByUserId(userDTO.getUserId());
		if(!Objects.isNull(userCheck)) {
			logger.error("User already exists. Please choose a different UserId");
			throw new UserException("User already exists. Please choose a different UserId");
		}
		User user = new User();
		user.setUserId(userDTO.getUserId());
		user.setPassword(userDTO.getPassword());
		user.setName(userDTO.getName());
		user.setCity(userDTO.getCity());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		
		userRepository.save(user);
		
		return userDTO.getUserId();
	}
	
	public String userLogin(String userId, String password) throws UserException {
	    User user = userRepository.findByUserId(userId);
	    if (Objects.isNull(user)) {
	        logger.error("Invalid credentials: User not found for userId = {}", userId);
	        throw new UserException("Invalid credentials: User not found");
	    }
	    if (!password.equals(user.getPassword())) {
	        logger.error("Invalid credentials: Incorrect password for userId = {}", userId);
	        throw new UserException("Invalid credentials: Incorrect password");
	    }
	    return user.getName();
	}

	
	public UserDTO getUserDetails(String userId) throws UserException{
		User user = userRepository.findByUserId(userId);
		if(Objects.isNull(user)) {
			logger.error("User is not available");
			throw new UserException("User is not available: {}"+userId);
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(user.getUserId());
		userDTO.setName(user.getName());
		userDTO.setCity(user.getCity());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());
		return userDTO;
	}
	
	public List<UserDTO> getAllUserDetails() throws UserException{
		List<User> users = (List<User>) userRepository.findAll();
		if(Objects.isNull(users)) {
			logger.error("User is not available");
			throw new UserException("User is not available");
		}
		List<UserDTO> userDTOs = new ArrayList<>();
		for(User user: users) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(user.getUserId());
			userDTO.setName(user.getName());
			userDTO.setCity(user.getCity());
			userDTO.setEmail(user.getEmail());
			userDTO.setPhone(user.getPhone());
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}
	
	public void updatePassword(String email, String phone, String password) throws UserException {
	    User user = userRepository.findByEmail(email);
	    if (Objects.isNull(user)) {
	        throw new UserException("Email not found for any user");
	    }
	    int rowCount = userRepository.updatePassword(email, phone, password);
	    if (rowCount <= 0) {
	        throw new UserException("Phone number is not associated with mail");
	    }
	}

}
