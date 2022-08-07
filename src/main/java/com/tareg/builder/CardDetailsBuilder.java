package com.tareg.builder;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CardDetailsBuilder {
    private String id;
    private String name;
    private String credNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expire;
}
