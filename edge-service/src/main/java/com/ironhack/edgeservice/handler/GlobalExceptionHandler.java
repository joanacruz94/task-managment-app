package com.ironhack.edgeservice.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ironhack.edgeservice.DTO.ErrorResponse;
import com.ironhack.edgeservice.exceptions.BadRequestException;
import com.ironhack.edgeservice.exceptions.ConflictException;
import com.ironhack.edgeservice.exceptions.NotFoundException;
import com.netflix.client.ClientException;
import feign.FeignException;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class, NoSuchFieldException.class, NumberFormatException.class, JsonProcessingException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse runtime(RuntimeException exception) {
        log.info(exception.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundHandler(NotFoundException notFoundException) {
        log.info(notFoundException.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), notFoundException.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse conflictHandler(ConflictException conflictException) {
        log.info(conflictException.getMessage());
        return new ErrorResponse(HttpStatus.CONFLICT.value(), conflictException.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse httpClientErrorHandler(HttpClientErrorException httpClientErrorException) {
        log.info(httpClientErrorException.getMessage());
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), httpClientErrorException.getMessage());
    }

    @ExceptionHandler(ClientException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleFeignServiceDown(ClientException clientException) {
        log.info(clientException.getMessage());
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), clientException.getMessage());
    }

    @ExceptionHandler(RetryableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleFeignConnectionDown(RetryableException exception) {
        log.info(exception.getMessage());
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(FeignException.class)
    public ErrorResponse handleFeignException(FeignException exception) {
        String message = exception.contentUTF8().split(",")[1].split(":")[1].replaceAll("\"|}", "");
        log.info(message);
        return new ErrorResponse(exception.status(), message);
    }
}