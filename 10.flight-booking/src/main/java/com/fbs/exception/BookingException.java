package com.fbs.exception;

public class BookingException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public BookingException(String msg) {
		super(msg);
	}
}