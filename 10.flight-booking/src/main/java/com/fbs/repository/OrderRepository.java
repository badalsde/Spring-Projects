package com.fbs.repository;

import org.springframework.data.repository.CrudRepository;

import com.fbs.entity.Orders;

public interface OrderRepository extends CrudRepository<Orders,Integer>{
	public Orders findByRazorpayOrderId(String razorpayId);
}