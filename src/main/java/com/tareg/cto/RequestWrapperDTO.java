package com.tareg.cto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestWrapperDTO {

    private Object request;
    private Object response;
    private List<RegistrationError> errors;
    private List<RegistrationStatus> status;
    private LocalDateTime timeStamp;

}
