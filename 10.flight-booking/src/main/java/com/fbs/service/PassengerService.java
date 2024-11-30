//package com.fbs.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.fbs.dto.PassengerDTO;
//import com.fbs.entity.Passenger;
//import com.fbs.exception.PassengerException;
//import com.fbs.repository.PassengerRepository;
//
//@Service("passengerService")
//@Transactional
//public class PassengerService {
//
//    @Autowired
//    private PassengerRepository passengerRepository;
//
//    @Transactional
//    public List<Passenger> savePassengers(List<PassengerDTO> passengerDTOs) throws PassengerException{
//        if (passengerDTOs.size() > 5) {
//            throw new PassengerException("You can only add up to 5 passengers.");
//        }
//
//        List<Passenger> passengers = passengerDTOs.stream().map(dto -> {
//            Passenger passenger = new Passenger();
//            passenger.setPassengerName(dto.getPassengerName());
//            passenger.setPassengerAge(dto.getPassengerAge());
//            passenger.setPassengerGender(dto.getPassengerGender());
//            passenger.setUserId(dto.getUserId());
//            return passenger;
//        }).toList();
//
//        return (List<Passenger>) passengerRepository.saveAll(passengers);
//    }
//
//}
