package com.learning.exception;

import lombok.ToString;

@ToString(callSuper = true)


public class IdNotFoundException extends Exception{
	
	public IdNotFoundException(String msg) {
		super(msg);
		
	}
	
	

}
