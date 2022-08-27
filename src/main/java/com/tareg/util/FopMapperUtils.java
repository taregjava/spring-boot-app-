package com.tareg.util;

import com.tareg.builder.CardDetailsBuilder;
import com.tareg.cto.CardDetailsDto;
import com.tareg.entity.CardDetails;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class FopMapperUtils {

    private FopMapperUtils(){

    }
    public static CardDetailsBuilder mapToFopBuilder(CardDetails entity){
        return CardDetailsBuilder.builder()
                .id(entity.getId())
                .name(entity.getName())
                .credNumber(entity.getCredNumber())
                .expire(entity.getExpire())
                .build();
    }

   /* public static List<CardDetails> mapToCardDetailsEntity(List<CardDetails> dtos){
        return dtos.stream().map(FopMapperUtils::mapToCardDetailsEntity).collect(Collectors.toList());
    }*/

    public static CardDetails mapToCardDetailsEntity(CardDetailsDto dto){
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
}
