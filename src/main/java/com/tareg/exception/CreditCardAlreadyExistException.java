package com.tareg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class CreditCardAlreadyExistException extends RuntimeException {

    public CreditCardAlreadyExistException(String ccNumber) {
        super("credit card already exist: '" + ccNumber + "'");
    }
}
