package com.intern.controller;

import com.intern.dto.MentorDTO;
import com.intern.dto.ProjectDTO;
import com.intern.exception.InternException;
import com.intern.service.ProjectAllocationService;
import com.intern.service.ProjectAllocationServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intern")
@Validated
public class ProjectAllocationAPI {

    @Autowired
    private ProjectAllocationServiceImpl projectService;
    @Autowired
    private Environment environment;

    @PostMapping(value = "/project", consumes = "application/json")
    public ResponseEntity<String> allocateProject(@Valid @RequestBody ProjectDTO project) throws InternException
    {
        Integer projectId = projectService.allocateProject(project);
        String successMessage = environment.getProperty("API.ALLOCATION_SUCCESS")+": "+projectId;
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    @GetMapping(value = "mentor/{noOfProjects}")
    public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable("noOfProjects") Integer numberOfProjectsMentored) throws InternException
    {
        List<MentorDTO> responses = projectService.getMentors(numberOfProjectsMentored);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PutMapping("project/{projectId}/{mentorId}")
    public ResponseEntity<String> updateProjectMentor(@PathVariable("projectId") Integer projectId,@PathVariable("mentorId") @Min(value = 1000,message = "{mentor.mentorid.invalid}")
    @Max(value = 9999,message = "{mentor.mentorid.invalid}") Integer mentorId) throws InternException
    {
        projectService.updateProjectMentor(projectId, mentorId);
        String successMessage = environment.getProperty("API.PROJECT_UPDATE_SUCCESS");
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    @DeleteMapping(value = "project/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable("projectId") Integer projectId) throws InternException
    {
        projectService.deleteProject(projectId);
        String successMessage = environment.getProperty("API.PROJECT_DELETE_SUCCESS");
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }


}
