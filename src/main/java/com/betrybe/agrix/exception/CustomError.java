package com.betrybe.agrix.exception;

/**
 * CustomError class.
 */
public class CustomError extends RuntimeException {
  int status;

  public CustomError(String message, int status) {
    super(message);
    this.status = status;
  }

  public int getStatus() {
    return this.status;
  }

}