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
public class RequestWrapperDTO<T, U, W> {

    private T request;
    private U response;
    private W pageDetails;
    private List<StatusResponse> errors;
    private List<StatusResponse> status;
    private LocalDateTime timeStamp;

}
