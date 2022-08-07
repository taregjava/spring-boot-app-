package com.tareg.util;

import com.tareg.builder.CardDetailsBuilder;
import com.tareg.entity.CardDetails;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<String, Object> buildForObjectMapper(String key, Object value) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}
