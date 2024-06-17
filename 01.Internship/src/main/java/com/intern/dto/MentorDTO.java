package com.intern.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MentorDTO {
    @NotNull(message = "{mentor.mentorid.absent}")
    @Min(value = 1000,message = "{mentor.mentorid.invalid}")
    @Max(value = 9999,message = "{mentor.mentorid.invalid}")
    private Integer mentorId;
    private String mentorName;
    private Integer numberOfProjectsMentored;
}
