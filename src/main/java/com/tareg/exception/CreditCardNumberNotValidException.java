package com.tareg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CreditCardNumberNotValidException extends RuntimeException {


    public CreditCardNumberNotValidException(String ccNumber) {
        super("credit card number not valid: '" + ccNumber + "'");
    }
}
