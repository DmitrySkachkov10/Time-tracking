package by.krainet.dmitry_skachkov.account_service.controller;

import by.dmitryskachkov.dto.ExcepionResponseDto;
import by.dmitryskachkov.exception.exceptions.ValidationException;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalHandlerController {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExcepionResponseDto> defaultExceptionHandler(ValidationException e) {
        return new ResponseEntity<>(new ExcepionResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OptimisticEntityLockException.class)
    public ResponseEntity<ExcepionResponseDto> defaultExceptionHandler(OptimisticEntityLockException e) {
        return new ResponseEntity<>(new ExcepionResponseDto(""), HttpStatus.CONFLICT);
    }

}
