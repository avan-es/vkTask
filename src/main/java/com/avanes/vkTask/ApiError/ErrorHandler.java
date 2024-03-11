package com.avanes.vkTask.ApiError;


import com.avanes.vkTask.ApiError.exception.ConflictException;
import com.avanes.vkTask.ApiError.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;


@RestControllerAdvice
public class ErrorHandler {



    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorResponse onConflictException(ConflictException e) {
        return new ErrorResponse(HttpStatus.CONFLICT,
                "Integrity constraint has been violated.",
                e.getMessage(),
                LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse onNotFoundException(NotFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND,
                "Объект не найден.",
                e.getMessage(),
                LocalDateTime.now());
    }

}