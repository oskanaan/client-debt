package com.thebinaryheap.clientdebt.controller;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

  @ExceptionHandler({ConstraintViolationException.class, DataIntegrityViolationException.class})
  ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest req) {

    String causeMessage = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
    if (causeMessage.contains("REFERENCE_NUMBER")) {
      return new ResponseEntity<>("Reference number is already in use.", HttpStatus.CONFLICT);
    }

    return new ResponseEntity<>(causeMessage, HttpStatus.CONFLICT);
  }
}
