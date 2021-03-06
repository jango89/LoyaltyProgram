package com.trivago.catalogue.controller;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);

  @ExceptionHandler(value = {MethodArgumentNotValidException.class, IllegalArgumentException.class})
  @ResponseStatus(EXPECTATION_FAILED)
  public String validationException(Exception exp) {
    LOGGER.error("Validation exception", exp);
    return exp.getMessage();
  }

  @ExceptionHandler(value = RuntimeException.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public void generalRunTimeException(RuntimeException exp) {
    LOGGER.error("Runtime exception", exp);
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(SERVICE_UNAVAILABLE)
  public void generalException(Exception exp) {
    LOGGER.error("Exception", exp);
  }
}
