package com.wecare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coach {
    @Id
    @Column(name = "coach_id")
    private String coachId;
    private String name;
    private char gender;
    private LocalDate dateOfBirth;
    private String password;
    private String mobileNumber;
    private String speciality;
}
