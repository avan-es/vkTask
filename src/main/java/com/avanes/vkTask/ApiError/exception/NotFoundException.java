package com.avanes.vkTask.ApiError.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String error) {
        super(error);
    }

}