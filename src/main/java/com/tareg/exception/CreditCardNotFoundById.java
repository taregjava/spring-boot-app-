package com.tareg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CreditCardNotFoundById extends RuntimeException {


    public CreditCardNotFoundById(String name) {
        super("could not find credit card for name: '" + name + "'");
    }
}
