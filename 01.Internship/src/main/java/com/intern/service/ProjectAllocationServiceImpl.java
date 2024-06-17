package com.intern.service;

import com.intern.dto.MentorDTO;
import com.intern.dto.ProjectDTO;
import com.intern.entity.Mentor;
import com.intern.entity.Project;
import com.intern.exception.InternException;
import com.intern.repository.MentorRepository;
import com.intern.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("projectAllocationService")
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService{

    @Autowired
    private MentorRepository mentorRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private Environment environment;

    @Override
    public Integer allocateProject(ProjectDTO projectDTO) throws InternException {
        Optional<Mentor> optionalMentor = mentorRepository.findById(projectDTO.getMentorDTO().getMentorId());
        Mentor mentor = optionalMentor.orElseThrow(()->new InternException(environment.getProperty("Service.MENTOR_NOT_FOUND")));
        if(mentor.getNumberOfProjectsMentored()>=3){
            throw new InternException(environment.getProperty("Service.CANNOT_ALLOCATE_PROJECT"));
        }
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setIdeaOwner(projectDTO.getIdeaOwner());
        project.setReleaseDate(projectDTO.getReleaseDate());
        project.setMentor(mentor);
        projectRepository.save(project);
        mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
        mentorRepository.save(mentor);
        return mentor.getMentorId();
    }

    @Override
    public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InternException {
        List<Mentor> mentors = mentorRepository.findByNumberOfProjectsMentored(numberOfProjectsMentored);
        if (mentors.isEmpty()){
            throw new InternException(environment.getProperty("Service.MENTOR_NOT_FOUND"));
        }
        List<MentorDTO> mentorDTOS = new ArrayList<>();
        for(Mentor mentor:mentors){
            MentorDTO mentorDTO = new MentorDTO();
            mentorDTO.setMentorId(mentor.getMentorId());
            mentorDTO.setMentorName(mentor.getMentorName());
            mentorDTO.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored());
            mentorDTOS.add(mentorDTO);
        }
        return mentorDTOS;
    }

    @Override
    public void updateProjectMentor(Integer projectId, Integer mentorId) throws InternException {
        Optional<Mentor> mentorOptional = mentorRepository.findById(mentorId);
        Mentor mentor = mentorOptional.orElseThrow(()->new InternException(environment.getProperty("Service.MENTOR_NOT_FOUND")));
        if(mentor.getNumberOfProjectsMentored()>=3){
            throw new InternException(environment.getProperty("Service.CANNOT_ALLOCATE_PROJECT"));
        }

        Optional<Project> projectOptional = projectRepository.findById(projectId);
        Project project = projectOptional.orElseThrow(()->new InternException(environment.getProperty("Service.PROJECT_NOT_FOUND")));
        project.setMentor(mentor);
        mentor.setNumberOfProjectsMentored(project.getMentor().getNumberOfProjectsMentored()+1);
        projectRepository.save(project);
        mentorRepository.save(mentor);
    }

    @Override
    public void deleteProject(Integer projectId) throws InternException {
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        Project project = projectOptional.orElseThrow(()->new InternException(environment.getProperty("Service.PROJECT_NOT_FOUND")));
        if(project.getMentor() != null){
            Mentor mentor = project.getMentor();
            mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()-1);
            mentorRepository.save(mentor);
        }
        projectRepository.delete(project);
    }
}
