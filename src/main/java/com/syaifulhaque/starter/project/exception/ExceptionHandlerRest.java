package com.syaifulhaque.starter.project.exception;

import com.syaifulhaque.starter.project.common.Constants;
import com.syaifulhaque.starter.project.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerRest {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ResponseDto> exceptionHandler(Exception e) {
        log.error(Constants.ERROR_LOG, e.getMessage());

        return ResponseEntity.badRequest()
                .body(ResponseDto.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .message(e.getClass().getSimpleName() + ".class Unexpected Error")
                        .errors(new String[]{e.getMessage()})
                        .build()
                );
    }

    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<ResponseDto> noSuchElementException(NoSuchElementException e) {
        log.error(Constants.ERROR_LOG, e.getMessage());

        return ResponseEntity.badRequest()
                .body(ResponseDto.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .timestamp(LocalDateTime.now())
                        .message(Constants.NOT_FOUND_EXCEPTION)
                        .errors(new String[]{Constants.NOT_FOUND_EXCEPTION})
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(Constants.ERROR_LOG, e.getMessage());

        String[] errors = e.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " " + f.getDefaultMessage())
                .toArray(String[]::new);

        return ResponseEntity.badRequest()
                .body(ResponseDto.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .message("Arguments not valid")
                        .errors(errors)
                        .build()
                );
    }
}
