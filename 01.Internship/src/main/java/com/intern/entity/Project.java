package com.intern.entity;

import com.intern.dto.MentorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;
    private String projectName;
    private Integer ideaOwner;
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "mentor_id", referencedColumnName ="mentor_id")
    private Mentor mentor;
}
