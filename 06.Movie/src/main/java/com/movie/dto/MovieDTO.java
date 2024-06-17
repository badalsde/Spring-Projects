package com.movie.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private int movieId;
    @NotNull(message = "Movie Name must not be Null!!")
    @NotEmpty(message = "Movie Name must not be Empty!!")
    @NotBlank(message = "Movie Name must not be Blank!!")
    private String name;
    @NotNull(message = "Movie rating must not be Null!!")
    @Min(value = 0, message = "Movie rating must be between 0 and 10")
    @Max(value = 10, message = "Movie rating must be between 0 and 10")
    private Float rating;
}
