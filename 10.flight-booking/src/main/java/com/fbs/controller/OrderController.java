package com.fbs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.entity.Orders;
import com.fbs.service.OrderService;
import com.razorpay.RazorpayException;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping(value = "/createOrder", produces = "application/json")
	public ResponseEntity<Orders> createOrder(@RequestBody Orders orders) throws RazorpayException{
		Orders razorpayOrder = orderService.createOrder(orders);
		return new ResponseEntity<>(razorpayOrder,HttpStatus.CREATED);
	}
	
	@PostMapping("/paymentCallback")
	public ResponseEntity<String> paymentCallback(@RequestParam Map<String, String> response) {
		orderService.updateStatus(response);
		return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"Payment successful!\"}");
		
	}
}
