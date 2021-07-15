package com.godeltech.bikesharing.controller.handler;

import com.godeltech.bikesharing.exception.ResourceNotFoundException;
import com.godeltech.bikesharing.models.response.error.GeneralError;
import com.godeltech.bikesharing.models.response.error.InternalServerError;
import com.godeltech.bikesharing.models.response.error.ResourceNotFoundError;
import com.godeltech.bikesharing.models.response.error.ValidationError;
import com.godeltech.bikesharing.models.response.error.Violation;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<GeneralError> handleNotFoundException(ResourceNotFoundException e) {
    log.error(e.getMessage(), e);
    var error = newResourceNotFoundError(e);
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  private GeneralError newResourceNotFoundError(ResourceNotFoundException e) {
    var error = new ResourceNotFoundError();
    error.setMessage(e.getMessage());
    error.setExceptionCode(ResourceNotFoundError.EXCEPTION_CODE);
    return error;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<GeneralError> handleConstraintValidationException(ConstraintViolationException e) {
    final List<Violation> violations = e.getConstraintViolations().stream()
        .map(
            violation -> new Violation(
                violation.getPropertyPath().toString(),
                violation.getMessage()
            )
        )
        .collect(Collectors.toList());
    log.error(violations.toString(), e);
    var error = newValidationError(e, violations);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  private GeneralError newValidationError(Exception e, List<Violation> violations) {
    var error = new ValidationError();
    error.setMessage(e.getMessage());
    error.setViolations(violations);
    error.setExceptionCode(ValidationError.EXCEPTION_CODE);
    return error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<GeneralError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    final List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
        .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
        .collect(Collectors.toList());
    log.error(violations.toString(), e);
    var error = newValidationError(e, violations);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<GeneralError> handleUncaughtException(Exception e) {
    log.error(e.getMessage(), e);
    var error = newInternalServerError(e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  private GeneralError newInternalServerError(Exception e) {
    var error = new InternalServerError();
    error.setMessage(e.getMessage());
    error.setExceptionCode(InternalServerError.EXCEPTION_CODE);
    return error;
  }
}
