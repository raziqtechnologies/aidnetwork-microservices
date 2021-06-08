package com.rts.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordException extends RuntimeException 
{
    public RecordException(String exception) {
        super(exception);
    }
}