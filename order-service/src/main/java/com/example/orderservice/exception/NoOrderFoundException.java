package com.example.orderservice.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoOrderFoundException extends RuntimeException{
    public NoOrderFoundException(String message){
        super(message);
    }
}
