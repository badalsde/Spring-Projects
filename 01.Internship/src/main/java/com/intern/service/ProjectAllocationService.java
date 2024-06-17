package com.intern.service;

import com.intern.dto.MentorDTO;
import com.intern.dto.ProjectDTO;
import com.intern.exception.InternException;

import java.util.List;

public interface ProjectAllocationService {
    public Integer allocateProject(ProjectDTO projectAllocation) throws InternException;
    public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InternException;
    public void updateProjectMentor(Integer projectId, Integer mentorId) throws InternException;
    public void deleteProject(Integer projectId) throws InternException;
}
