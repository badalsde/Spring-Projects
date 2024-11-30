//package com.fbs.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fbs.dto.PassengerDTO;
//import com.fbs.entity.Passenger;
//import com.fbs.exception.PassengerException;
//import com.fbs.service.PassengerService;
//
//import jakarta.validation.Valid;
//
//@CrossOrigin(origins="http://localhost:3000")
//@RestController
//@RequestMapping("/passenger")
//@Validated
//public class PassengerController {
//
//	@Autowired
//	private PassengerService passengerService;
//	
//	@PostMapping
//    public ResponseEntity<List<Passenger>> addPassengers(@Valid @RequestBody List<PassengerDTO> passengerDTOs) throws PassengerException{
//		List<Passenger> savedPassengers = passengerService.savePassengers(passengerDTOs);
//		return new ResponseEntity<>(savedPassengers,HttpStatus.OK);
//    }
//}