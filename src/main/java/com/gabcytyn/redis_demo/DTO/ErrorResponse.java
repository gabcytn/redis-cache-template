package com.gabcytyn.redis_demo.DTO;

public class ErrorResponse {
  private String message;

  public ErrorResponse() {}

  public ErrorResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ErrorResponse{" + "message='" + message + '\'' + '}';
  }
}
