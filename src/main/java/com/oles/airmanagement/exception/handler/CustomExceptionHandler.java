package com.oles.airmanagement.exception.handler;

import com.oles.airmanagement.dto.exception.ExceptionMapResponse;
import com.oles.airmanagement.dto.exception.ExceptionResponse;
import com.oles.airmanagement.exception.AlreadyExistException;
import com.oles.airmanagement.exception.NotFoundException;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        return buildExceptionBody(exception, NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public final ResponseEntity<Object> handleAlreadyExistException(AlreadyExistException exception) {
        return buildExceptionBody(exception, CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleAlreadyExistException(DataIntegrityViolationException exception) {
        return buildExceptionBody(new Exception(exception.getMostSpecificCause().getMessage()), CONFLICT);
    }

    private ResponseEntity<Object> buildExceptionBody(Exception exception, HttpStatus httpStatus) {
        ExceptionResponse exceptionResponse = ExceptionResponse
            .builder()
            .status(httpStatus)
            .message(exception.getMessage())
            .timeStamp(Instant.now())
            .build();
        return ResponseEntity
            .status(httpStatus)
            .body(exceptionResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, String> map = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(exc -> map.put(exc.getField(), exc.getDefaultMessage()));

        ExceptionMapResponse exceptionMapResponse = ExceptionMapResponse
            .builder()
            .status(status)
            .messageMap(map)
            .timeStamp(Instant.now())
            .build();
        return ResponseEntity.status(status).body(exceptionMapResponse);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return buildExceptionBody(ex, status);
    }
}
