package com.tareg.util;

import com.tareg.builder.CardDetailsBuilder;
import com.tareg.cto.CardDetailsDto;
import com.tareg.entity.CardDetails;
import com.tareg.enums.CardType;
import com.tareg.helper.AESExample;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class CreditCardBuilderUtils {

    public static CardDetailsBuilder getRegistrationBuilder(CardDetails dto) {
        CardDetailsBuilder buildData = null;

        buildData = CardDetailsBuilder.builder().id(String.valueOf(dto.getId()))
                .name(dto.getName())
                .credNumber(AESExample.decrypt(dto.getCredNumber()))
                .expire(dto.getExpire())
              //  .list(dto.getList())
              //  .cardType(Collections.singletonList(String.valueOf(dto.getCardType())))
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
            entity.setCredNumber(AESExample.encrypt(dto.getCredNumber()));
        if (StringUtils.isNotBlank(String.valueOf(dto.getExpire())))
            entity.setExpire(dto.getExpire());
       /* if (StringUtils.isNotBlank(dto.getCardType()))
            entity.setCardType(CardType.valueOf(dto.getCardType()));*/
      /*  if (StringUtils.isNotBlank(dto.getExpirationDate()))
            entity.setExpirationDate(dto.getExpirationDate());*/
       /* if (CollectionUtils.isNotEmpty(dto.getList()))
            entity.setList(dto.getList());*/
       /* if(StringUtils.isNotBlank(String.valueOf(dto.getCardType())))
        entity.setCardType(CardType.valueOfDesc(String.valueOf(dto.getCardType())));*/
        return entity;
    }

    public static Map<String, Object> buildForObjectMapper(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
