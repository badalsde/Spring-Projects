package com.wecare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String userId;
    @NotNull(message = "{user.name.absent}")
    @Size(min = 3,max = 20,message = "{user.name.invalid}")
    private String name;
    private char gender;
    private LocalDate dateOfBirth;
    @NotNull(message = "{user.password.absent}")
    @Size(min = 5,max = 10,message = "{user.password.invalid}")
    private String password;
    @NotNull(message = "{user.phoneNo.absent}")
    @Size(min = 10,max = 10,message = "{user.phoneNo.invalid}")
    private String mobileNumber;
    @Email(message = "{user.email.invalid}")
    private String email;
    @NotNull(message = "{user.pinCode.absent}")
    @Size(min = 6,max = 6,message = "{user.pinCode.invalid}")
    private String pincode;
    @NotNull(message = "{user.city.absent}")
    @Size(min = 3,max = 20,message = "{user.city.invalid}")
    private String city;
    @NotNull(message = "{user.state.absent}")
    @Size(min = 3,max = 20,message = "{user.state.invalid}")
    private String state;
    @NotNull(message = "{user.country.absent}")
    @Size(min = 3,max = 20,message = "{user.country.invalid}")
    private String country;
}
