package com.weather.app.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.weather.app.error.ErrorDetails;
@RestControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {
	@ExceptionHandler(HttpClientErrorException.class)
	   protected ResponseEntity<Object> handleEntityNotFound(
			   HttpClientErrorException ex) {
	       ErrorDetails apiError = new ErrorDetails(ex.getStatusCode(),ex.getMessage());
	       return buildResponseEntity(apiError);
	   }

	private ResponseEntity<Object> buildResponseEntity(ErrorDetails apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());	}

}
