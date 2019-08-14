package com.kevin.man.busrouteapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    // Custom exception class for returning status code 404.
    public NotFoundException (final String message) {super(message); }
}
