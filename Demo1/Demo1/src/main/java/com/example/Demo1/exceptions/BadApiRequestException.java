package com.example.Demo1.exceptions;

public class BadApiRequestException extends RuntimeException {
  public BadApiRequestException() {
    super("Bad API Request");
  }

  public BadApiRequestException(String message) {
    super(message);
  }
}
