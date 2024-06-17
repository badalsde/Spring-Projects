package com.cabbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cab {
    @Id
    @Column(name = "cab_no")
    private Integer cabNo;
    private String modelName;
    private Long driverPhoneNo;
    private String availability;
}
