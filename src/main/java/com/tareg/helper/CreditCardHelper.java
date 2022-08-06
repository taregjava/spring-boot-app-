package com.tareg.helper;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreditCardHelper {


    /*
    *
    *   public CreditCard add(String name, String ccNumber, int limit) throws CreditCardAlreadyExistException, CreditCardNumberNotValidException {
        if (CreditCardHelper.isValid(ccNumber)) {
            if (creditCardRepository.findByName(name) != null) {
                throw new CreditCardAlreadyExistException(ccNumber);
            } else {
                CreditCard creditCard = new CreditCard(name, ccNumber, limit);
                return creditCardRepository.save(creditCard);
            }
        } else {
            throw new CreditCardNumberNotValidException(ccNumber);
        }
    }
    *
    * */
    public static boolean Check(Long ccNumber)
    {
        if (ccNumber==null) {
            throw new IllegalArgumentException("Credit card number must not be blank");
        }
        if (ccNumber > 20) {
            throw new IllegalArgumentException("Card number may not be more than 19 digits long");
        }
        int sum = 0;
        boolean alternate = false;
        for (int i = (int) (ccNumber - 1); i >= 0; i--)
        {
           int n = 0;
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
    public static boolean isValid(String ccNumber) throws IllegalArgumentException {

        if (StringUtils.isBlank(ccNumber)) {
            throw new IllegalArgumentException("Credit card number must not be blank");
        }
        if (ccNumber.length() > 20) {
            throw new IllegalArgumentException("Card number may not be more than 19 digits long");
        }
      /*  try {
           Long number=Long.parseInt(ccNumber);
            System.out.println(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Card number must contain only digits");
        }*/


        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public static String maskCardNumber(String cardNumber) {


        int index = 0;
        String mask = "************####";
        StringBuilder maskedNumber = new StringBuilder();
        for (int i = 0; i < mask.length(); i++) {
            char c = mask.charAt(i);
            if (c == '#') {
                maskedNumber.append(cardNumber.charAt(index));
                index++;
            } else if (c == '*') {
                maskedNumber.append(c);
                index++;
            } else {
                maskedNumber.append(c);
            }
        }

        return maskedNumber.toString();
    }
    public static boolean isValidCreditCard(String creditCardNo) {

        String visaRegex = "^4[0-9]{12}(?:[0-9]{3})?$";
        String masterRegex = "^5[1-5][0-9]{14}$";
        String expressRegex = "^3[47][0-9]{13}$";


        if (creditCardNo.matches(visaRegex)) {
            return true;
        } else if (creditCardNo.matches(masterRegex)) {
            return true;
        } else if (creditCardNo.matches(expressRegex)) {
            return true;
        }

        return false;

    }

   /* public static void main(String[] args) {
       String cardNumber= "3793545081623065";
       System.out.println(maskCardNumber(cardNumber));

       // isValid("03555");
 String cType = null;

        System.out.println("Enter a credit card number: ");
        final Scanner input = new Scanner(System.in);
        final String cardNumber = input.next();

        if (cardNumber.startsWith("4"))
        {
            cType = "Visa";
        }
        else if (cardNumber.startsWith("5"))
        {
            cType =  "MasterCard";
        }
        else if (cardNumber.startsWith("6"))
        {
            cType =  "Discover";
        }
        else if (cardNumber.startsWith("37"))
        {
            cType =  "American Express";
        }
        else
        {
            cType =  "Unknown type";
        }

        final long total = sumOfEvenPlaces(Long.valueOf(cardNumber)) + (sumOfOddPlaces(Long.valueOf(cardNumber)) * 2);

        if (isValid(total))
        {
            System.out.println("The " + cType + " card number is valid");
        }
        else
        {
            System.out.println("The " + cType + " card number is invalid.");
        }

    }*/
}
