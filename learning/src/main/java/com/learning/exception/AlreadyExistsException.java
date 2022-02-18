package com.learning.exception;

import lombok.ToString;

@ToString(callSuper = true)
public class AlreadyExistsException extends Exception{
	public AlreadyExistsException(String msg) {
		super(msg);
	}
}
