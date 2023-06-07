package com.example.springmvcrabbitmq.exception;

import com.example.springmvcrabbitmq.model.messages.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException exception, WebRequest request) {

        var response = new ApiErrorResponse<>();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setStatusCode(HttpStatus.NOT_FOUND.value());

        response.setPath(request.getDescription(false));
        response.setErrors(Arrays.asList(exception.getMessage()));

        log.error(response.toString());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);

    }

    @ExceptionHandler(InsufficientException.class)
    public ResponseEntity<?> handleInsufficientException(InsufficientException exception, WebRequest request) {
        var response = new ApiErrorResponse<>();
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setPath(request.getDescription(false));
        response.setErrors(Arrays.asList(exception.getMessage()));

        log.error(response.toString());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var response = new ApiErrorResponse<>();

        response.setPath(request.getDescription(false));
        response.setErrors(Arrays.asList(ex.getMessage(), "Required Type: " + ex.getRequiredType()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("MissingPathVariable");
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();

        errors.add(String.format("Number of errors : %s", ex.getErrorCount()));

        for (FieldError err : ex.getFieldErrors()) {
            errors.add(err.getField() + " : " + err.getDefaultMessage() + " Rejected Value: "
                    + err.getRejectedValue());

        }

        var response = new ApiErrorResponse<>();
        response.setPath(request.getDescription(false));
        response.setMessage("MethodArgumentNotValid");
        response.setErrors(errors);

        log.error(response.toString());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}

