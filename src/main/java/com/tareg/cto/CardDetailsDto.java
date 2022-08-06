package com.tareg.cto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class CardDetailsDto {
    private String id;
    private String name;
    private String credNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expire;
}
