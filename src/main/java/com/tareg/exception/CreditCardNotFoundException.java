package com.tareg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreditCardNotFoundException extends RuntimeException {


    public CreditCardNotFoundException(String name) {
        super("could not find credit card for name: '" + name + "'");
    }
}
