package com.tareg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CreditCardDateNotValidException  extends RuntimeException {


        public CreditCardDateNotValidException(String ccNumber) {
            super("credit card date not valid: '" + ccNumber + "'");
        }

}
