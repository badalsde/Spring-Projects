package com.wecare.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachDTO {
    private String coachId;
    @NotNull(message ="{coach.name.absent}")
    @Size(min=3,max = 20,message = "{coach.name.invalid}")
    private String name;
    private char gender;
    @PastOrPresent
    private LocalDate dateOfBirth;
    @NotNull(message = "{coach.password.absent}")
    @Size(min = 5,max = 10,message = "{coach.password.invalid}")
    private String password;
    @NotNull(message = "{coach.phoneNo.absent}")
    @Size(min = 10,max = 10,message = "{coach.phoneNo.invalid}")
    private String mobileNumber;
    @NotNull(message = "{coach.speciality.absent}")
    @Size(min = 3,max = 20,message = "{coach.speciality.invalid}")
    private String speciality;
}
