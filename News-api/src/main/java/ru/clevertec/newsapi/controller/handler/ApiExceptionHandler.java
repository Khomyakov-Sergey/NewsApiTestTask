package ru.clevertec.newsapi.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.newsapi.dto.exception.ApiError;
import ru.clevertec.newsapi.exception.EntityByIdNotFoundException;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityByIdNotFoundException.class)
    public ApiError handleEntityByIdNotFoundException (EntityByIdNotFoundException exception){
        log.info((exception.toString()));
        return new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ApiError handleDefaultException (RuntimeException exception){
        log.info((exception.toString()));
        return new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
