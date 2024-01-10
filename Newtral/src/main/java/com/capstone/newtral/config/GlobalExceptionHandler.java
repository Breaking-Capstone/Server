package com.capstone.newtral.config;

import com.capstone.newtral.Dto.CommonResponseDto;
import com.capstone.newtral.common.CommonResponse;
import com.capstone.newtral.config.exception.DuplicatedException;
import com.capstone.newtral.config.exception.LoginErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DuplicatedException.class)
    public ResponseEntity<CommonResponseDto> handleDuplicatedException(DuplicatedException duplicatedException) {
        log.error(duplicatedException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CommonResponseDto(CommonResponse.FAIL, duplicatedException.getMessage(), null));
    }

    @ExceptionHandler(value = LoginErrorException.class)
    public ResponseEntity<CommonResponseDto> handleDuplicatedException(LoginErrorException loginErrorException) {
        log.error(loginErrorException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CommonResponseDto(CommonResponse.FAIL, loginErrorException.getMessage(), null));
    }

}
