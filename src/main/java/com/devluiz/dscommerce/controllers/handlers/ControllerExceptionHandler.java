package com.devluiz.dscommerce.controllers.handlers;

import com.devluiz.dscommerce.dto.CustomError;
import com.devluiz.dscommerce.services.exceptions.DatabaseException;
import com.devluiz.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
