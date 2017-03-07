package com.malkomich.hashcode2017;

public class NoEnoughSpaceError extends Exception {

  /**
   * Generated UID
   */
  private static final long serialVersionUID = 6204368966099865213L;

  private String message;

  public NoEnoughSpaceError() {

  }

  public NoEnoughSpaceError(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
