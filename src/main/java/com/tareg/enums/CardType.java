package com.tareg.enums;

import com.tareg.constant.ConstantValues;
import com.tareg.entity.error.Error;
import com.tareg.entity.error.ValidationHandler;
import com.tareg.exception.CreditCardNumberNotValidException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum CardType {

    CARD("CARD","Card"),
    CREDIT("CREDIT","Credit");

    private CardType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private final String code;
    private final String description;

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
    private static final Map<String, CardType> BY_CARD_TYPE= new HashMap<>();
   // Collection<InterestsEnum> interests;


    static {
        Arrays.stream(values())
                .forEach(
                        e -> BY_CARD_TYPE.put(e.description, e)
                );
    }

    public static CardType valueOfDesc(String label) {
        if (BY_CARD_TYPE.get(label) != null)
            for (CardType myVar : CardType.values()) {
                return BY_CARD_TYPE.get(myVar);
            }
        return null;
    }
}
