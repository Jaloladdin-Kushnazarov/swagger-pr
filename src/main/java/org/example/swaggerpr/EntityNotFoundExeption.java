package org.example.swaggerpr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class EntityNotFoundExeption extends RuntimeException {

    public EntityNotFoundExeption(String message) {
        super(message);
    }
}
