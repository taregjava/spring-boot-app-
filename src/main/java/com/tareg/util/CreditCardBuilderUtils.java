package com.tareg.util;

import com.tareg.builder.CardDetailsBuilder;
import com.tareg.cto.CardDetailsDto;
import com.tareg.entity.CardDetails;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreditCardBuilderUtils {

    public static CardDetailsBuilder getRegistrationBuilder(CardDetails s3config) {
        CardDetailsBuilder buildData = null;

        buildData = CardDetailsBuilder.builder().id(String.valueOf(s3config.getId()))
                .name(s3config.getName())
                .credNumber(s3config.getCredNumber())
                .expire(s3config.getExpire())

                .build();


        return buildData;
    }
   /* public static List<CardDetails> mapToCardDetailsEntity(List<CardDetails> dtos){
        return dtos.stream().map(CreditCardBuilderUtils::mapToCardDetailsEntity).collect(Collectors.toList());
    }
*/


    public static  CardDetails mapToCardDetailsEntity(CardDetailsDto dto){
        CardDetails entity= new CardDetails();
        entity.setId(dto.getId());
        if (StringUtils.isNotBlank(dto.getName()))
            entity.setName(dto.getName());
        if (StringUtils.isNotBlank(dto.getCredNumber()))
            entity.setCredNumber(dto.getCredNumber());
        if (StringUtils.isNotBlank(String.valueOf(dto.getExpire())))
            entity.setExpire(dto.getExpire());
      /*  if (StringUtils.isNotBlank(dto.getExpirationDate()))
            entity.setExpirationDate(dto.getExpirationDate());*/

        return entity;
    }

    public static Map<String, Object> buildForObjectMapper(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
