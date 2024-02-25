package com.todo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GloabalExceptionHandler extends RuntimeException {
	
	@ExceptionHandler(TodoNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTodoNotFoundException(TodoNotFoundException exception,
			WebRequest webRequest)
	{
		ErrorDetails errors = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"NOT_FOUND"
				);
		
		return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
	} 
	
}
