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
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String name;
    private char gender;
    private LocalDate dateOfBirth;
    private String password;
    private String mobileNumber;
    private String email;
    private String pincode;
    private String city;
    private String state;
    private String country;
}
