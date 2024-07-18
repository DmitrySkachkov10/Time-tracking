package by.krainet.dmitry_skachkov.timerackerservice.controller;

import by.dmitryskachkov.dto.ExcepionResponseDto;
import by.dmitryskachkov.exception.exceptions.ValidationException;
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

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExcepionResponseDto> defaultExceptionHandler(RuntimeException e) {
        ExcepionResponseDto responseDto = new ExcepionResponseDto(e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
