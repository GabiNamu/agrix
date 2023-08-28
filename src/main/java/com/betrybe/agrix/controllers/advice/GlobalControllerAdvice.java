package com.betrybe.agrix.controllers.advice;

import com.betrybe.agrix.exception.CustomError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * CustomError class.
 */
@ControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler(CustomError.class)
  public ResponseEntity<String> handleCustomException(CustomError exception) {
    return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
  }

}