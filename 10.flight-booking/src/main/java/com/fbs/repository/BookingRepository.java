package com.fbs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fbs.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking,Integer>{
	public Booking findByPnrNumber(String pnrNumber);
	public List<Booking> findByUserId(String userId);
}
