package com.fmordev.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {

    LocalDateTime now = LocalDateTime.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMM 'de' yyyy");
    String formattedTimestamp = now.format(formatter);

    ErrorDetails errorDetails = new ErrorDetails(
        formattedTimestamp,
        ex.getMessage(),
        webRequest.getDescription(false),
        "EMPLOYEE_NOT_FOUND"
    );
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }
}
