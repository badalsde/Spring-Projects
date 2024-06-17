package com.intern.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Integer projectId;

    @NotNull(message = "{project.projectname.absent}")
    private String projectName;

    @NotNull(message = "{project.ideaowner.absent}")
    private Integer ideaOwner;

    @NotNull(message = "{project.releasedate.absent}")
    private LocalDate releaseDate;

    @NotNull(message = "{project.mentor.absent}")
    private MentorDTO mentorDTO;
}
