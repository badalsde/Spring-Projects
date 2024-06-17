package com.flight.api;

import com.flight.dto.UserDTO;
import com.flight.dto.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@Valid @RequestBody UserDTO userDTO){

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@Valid @RequestBody UserLoginDTO userLoginDTO){

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
