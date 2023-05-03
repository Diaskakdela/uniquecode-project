package kz.geekpartners.uniquecodeservice.controller.advice;

import kz.geekpartners.uniquecodeservice.exception.UniqueCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UniqueCodeExceptionHandler {

    @ExceptionHandler(UniqueCodeException.class)
    public ResponseEntity<String> handleUniqueCodeNotFoundException(UniqueCodeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
