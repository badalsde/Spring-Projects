package com.cabbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabDTO {
    private Integer cabNo;
    private String modelName;
    private Long driverPhoneNo;
    private String availability;
}
