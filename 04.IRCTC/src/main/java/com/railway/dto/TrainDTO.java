package com.railway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainDTO {
    private Integer id;
    private String trainName;
    private String arrivalTime;
    private String departureTime;
    private double fare;
}
