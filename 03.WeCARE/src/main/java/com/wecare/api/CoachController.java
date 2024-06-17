package com.wecare.api;

import com.wecare.dto.BookingDTO;
import com.wecare.dto.CoachDTO;
import com.wecare.exception.WeCareException;
import com.wecare.service.CoachServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coaches")
@Validated
public class CoachController {
    @Autowired
    private Environment environment;
    @Autowired
    private CoachServiceImpl coachService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> createCoaches(@Valid @RequestBody CoachDTO coachDTO) throws WeCareException {
        coachService.createCoach(coachDTO);
        String successMessage = environment.getProperty("API.COACH_CREATION_SUCCESS")+": "+coachDTO.getCoachId();
        return null;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Boolean> coachLogin(@Valid @RequestBody CoachDTO coachDTO) throws WeCareException {
        Boolean result = coachService.coachLogin(coachDTO.getCoachId(), coachDTO.getPassword());
        return new ResponseEntity<>(result?HttpStatus.OK:HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(value = "/{coachId}")
    public ResponseEntity<CoachDTO> coachProfileDetails(@PathVariable("coachId") String coachId) throws WeCareException {
        CoachDTO result = coachService.getCoachDetails(coachId);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CoachDTO>> getAllCoaches() throws WeCareException {
        List<CoachDTO> result=coachService.getAllCoaches();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/booking/{coachId}")
    public ResponseEntity<List<BookingDTO>> getBookingDetails(@PathVariable("coachId") String coachId) throws WeCareException {
        List<BookingDTO> result = coachService.getBookingDetails(coachId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
