package com.fbs.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fbs.entity.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends CrudRepository<User,Integer>{
	
	public User findByUserId(String userId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE User u SET u.password = :password WHERE u.email = :email AND u.phone = :phone")
	public int updatePassword(@Param("email") String email, @Param("phone") String phone, @Param("password") String password);
	
	public User findByEmail(String email);
}
