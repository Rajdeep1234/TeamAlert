package com.alert.team.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.alert.exception.ClientCallException;
import com.alert.exception.GenericException;
import com.alert.exception.ValidaionException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(ClientCallException.class)
    public ResponseEntity<Object> handleCityNotFoundException(
    		ClientCallException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Client exception");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ValidaionException.class)
    public ResponseEntity<Object> handleCityNotFoundException(
    		ValidaionException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "validation exception");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(GenericException.class)
    public ResponseEntity<Object> handleCityNotFoundException(
    		GenericException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", " exception");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
}
