package br.com.giovanederenevick.cars.api.exceptions;

import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            EmptyResultDataAccessException.class,
            ObjectNotFoundException.class
    })
    public ResponseEntity errorNotFound(Exception ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity errorBadRequest(Exception ex) {
        return ResponseEntity.badRequest().build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
}
