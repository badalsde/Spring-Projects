package com.railway.dto;

import com.railway.entity.Train;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteDTO {
    private Integer id;
    private String source;
    private String destination;
    private List<TrainDTO> trainDTOList;
}
