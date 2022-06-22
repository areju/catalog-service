package com.polarbookshop.catalogservice.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.polarbookshop.catalogservice.domain.BookAlreadyExistsException;
import com.polarbookshop.catalogservice.domain.BookNotFoundException;

@RestControllerAdvice
public class BookControllerAdvice {
	
	@ExceptionHandler(BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String bookNotFoundExceptionHandler(BookNotFoundException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(BookAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	String bookAlreadyExistsExceptionHandler(BookAlreadyExistsException ex) {
		return ex.getMessage();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> validationExceptionHandler(MethodArgumentNotValidException ex) {
		
		Map<String,String> errors = new HashMap<>();
		
		ex.getAllErrors().forEach(error -> { String field = ((FieldError)error).getField(); 
					String msg = ((FieldError)error).getDefaultMessage();
					errors.put(field,msg);
		});
		
		return errors;
		
	}

}
