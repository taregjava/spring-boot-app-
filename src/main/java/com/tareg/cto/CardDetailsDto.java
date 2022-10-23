package com.tareg.cto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CardDetailsDto {
    private String id;
    private String name;
    private String credNumber;
    @JsonFormat(pattern="yyyy-MM")
    private Date expire;
   // private String cardType;
   // private List<String> cardType;
   // private List<String> list;
}
