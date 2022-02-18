package com.learning.ControllerAdvice;

import java.util.HashMap;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.exception.AlreadyExistsException;
import com.learning.exception.apierror.ApiError;


@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler{
//this class should be used when any userdefined exception is called through out all the controller
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<?> alreadyRecordExistsExceptionHandler(){
		
		HashMap<String, String> hashmap = new HashMap<>();
		hashmap.put("message","Record already Exists");
		return ResponseEntity.badRequest().body(hashmap);
		
		
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> ExceptionHandler(Exception e){
		
		HashMap<String, String> hashmap = new HashMap<>();
		hashmap.put("message","Unknown Exception - " + e.getMessage());
		return ResponseEntity.badRequest().body(hashmap);
		
		
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
	
	ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
	apiError.setMessage("Validation Error");
	apiError.addValidationErrors(ex.getBindingResult().getFieldErrors()); // fieldwise errors
	apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
	return buildResponseEntity(apiError);
		
	}

private ResponseEntity<Object> buildResponseEntity(ApiError	apiError){
	
	return new ResponseEntity<>(apiError,apiError.getHttpStatus());
}
@ExceptionHandler(ConstraintViolationException.class)
protected ResponseEntity<?> handleConstraintViolation() {
	return null;
	
}
}
