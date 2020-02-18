package com.belem.beerstore.service.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException() {
        super("beers-6", HttpStatus.NO_CONTENT);
    }
}
