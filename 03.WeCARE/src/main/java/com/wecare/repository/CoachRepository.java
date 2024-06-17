package com.wecare.repository;

import com.wecare.entity.Coach;
import org.springframework.data.repository.CrudRepository;

public interface CoachRepository extends CrudRepository<Coach,String> {
    public Coach findByCoachId(String coachId);

}
