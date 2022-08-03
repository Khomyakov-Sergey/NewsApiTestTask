package ru.clevertec.newsapi.controller.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.newsapi.dto.exception.ApiError;
import ru.clevertec.newsapi.exception.EntityByIdNotFoundException;

/**
 * Handler class for exceptions.
 * @author Siarhei Khamiakou
 * @version 1.0
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * This method handles EntityByIdNotFoundException.
     * @param exception - exception, which throw methods, if they can`t find entity with current id.
     * @return ApiError with text "Entity with ID=%d not found, please check your request" and status error.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityByIdNotFoundException.class)
    public ApiError handleEntityByIdNotFoundException (EntityByIdNotFoundException exception){
        return new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    /**
     * This method handles defaultException.
     * @param exception - default exception.
     * @return ApiError with text from exception.getMessage() and status error.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ApiError handleDefaultException (RuntimeException exception){
        return new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
