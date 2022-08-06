package com.tareg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CreditCardLimitExceededException extends RuntimeException {


    public CreditCardLimitExceededException(String name) {
        super("credit card limit exceeded for credit card: '" + name + "'");
    }
}