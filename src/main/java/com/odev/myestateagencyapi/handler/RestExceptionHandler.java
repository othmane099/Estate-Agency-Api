package com.odev.myestateagencyapi.handler;


import com.odev.myestateagencyapi.exception.EntityNotFoundException;
import com.odev.myestateagencyapi.exception.EstateAgencyException;
import com.odev.myestateagencyapi.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {



  @ExceptionHandler(InvalidEntityException.class)
  public ResponseEntity<ErrorDto> handleException(InvalidEntityException exception, WebRequest webRequest) {
    final HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    final ErrorDto errorDto = ErrorDto.builder()
            .code(exception.getErrorCode())
            .httpCode(badRequest.value())
            .message(exception.getMessage())
            .errors(exception.getErrors())
            .build();

    return new ResponseEntity<>(errorDto, badRequest);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorDto> handleException(EntityNotFoundException exception, WebRequest webRequest) {
    final HttpStatus badRequest = HttpStatus.NOT_FOUND;

    final ErrorDto errorDto = ErrorDto.builder()
            .code(exception.getErrorCode())
            .httpCode(badRequest.value())
            .message(exception.getMessage())
            .errors(exception.getErrors())
            .build();

    return new ResponseEntity<>(errorDto, badRequest);
  }

  @ExceptionHandler(EstateAgencyException.class)
  public ResponseEntity<ErrorDto> handleException(EstateAgencyException exception, WebRequest webRequest) {
    final HttpStatus badRequest = HttpStatus.NOT_ACCEPTABLE;

    final ErrorDto errorDto = ErrorDto.builder()
            .code(exception.getErrorCode())
            .httpCode(badRequest.value())
            .message(exception.getMessage())
            .build();

    return new ResponseEntity<>(errorDto, badRequest);
  }



}
