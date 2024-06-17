package com.intern.repository;

import com.intern.entity.Mentor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MentorRepository extends CrudRepository<Mentor,Integer> {
    List<Mentor> findByNumberOfProjectsMentored(Integer numberOfProjectsMentored);

}
