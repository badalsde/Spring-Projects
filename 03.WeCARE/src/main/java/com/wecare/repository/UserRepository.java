package com.wecare.repository;

import com.wecare.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    public User findByUserId(String userId);
}
