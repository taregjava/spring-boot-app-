package com.tareg.helper;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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

    public static boolean validate(final String n) {
        if (n == null || n.isEmpty()) return false;
        boolean x = true;
        int sum = 0;
        int temp = 0;

        for (int i = n.length() - 1; i >= 0; i--) {
            temp = n.charAt(i) - '0';
            sum += (x = !x) ? temp > 4 ? temp * 2 - 9 : temp * 2 : temp;
        }

        return sum % 10 == 0;

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
   public static boolean validate2(String creditCardNumber) {
        return algorithmCheck(creditCardNumber);
    }

    /*
     * @param int of number that should be split into individual
     * digits
     * @return the sum of the digits
     */
    private static int sumOfDigits(int number) {
        String[] tempNumberArray = Integer.toString(number).split("");
        int total = 0;
        for (String digit : tempNumberArray) {
            total += Integer.parseInt(digit);
        }

        return total;
    }
    public static boolean checkDate(String dateStr) {

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            sdf.parse(dateStr);

            int currentYear = Year.now().getValue();
            String str[] = dateStr.split("/");
            int year = Integer.parseInt(str[2]);

            if(year > currentYear || year < currentYear-100){
                return false;
            }

        } catch (NumberFormatException | ParseException ex){

            return false;  // Returns false if parsing fails (in case of bad input).
        }

        return true; // Returns true for valid date Strings
    }
  /*  public static boolean dateExpire(String datExpire) throws ParseException {
        String input = "11/12"; // for example
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
        simpleDateFormat.setLenient(false);
        Date expiry = simpleDateFormat.parse(datExpire);
        boolean expired = expiry.before(new Date());
        return expired;
    }*/
    public static List<Integer> parseNumber(String creditCardNumber) {
        List<Integer> creditCardNumberList = new ArrayList<>();

        for (char number : creditCardNumber.toCharArray()) {
            int tempNumber = Character.getNumericValue(number);
            creditCardNumberList.add(tempNumber);
        }

        return creditCardNumberList;
    }

    /*
     * @param credit card number as a List of Integers and
     * the IIN range to test
     * @return the IIN value to test
     */
    public static int parseIIN(List<Integer> creditCardNumberList, int range) {
        StringBuilder IINString = new StringBuilder(range);
        int IIN;

        for (int i = 0; i < range; i++) {
            IINString.append(creditCardNumberList.get(i));
        }
        IIN = Integer.parseInt(IINString.toString());

        return IIN;
    }
    private static boolean algorithmCheck(String creditCardNumber) {
        List<Integer> numberList = parseNumber(creditCardNumber);
        boolean isValid = false;
        int listSize = numberList.size();
        int[] tempArray = new int[listSize];
        int sum = 0;

        // Reverse the order
        Collections.reverse(numberList);

        // Double the value of every second digit
        for (int i = 1; i < listSize; i += 2) {
            int tempNumber = numberList.get(i) * 2;

            /*
             * If doubling results in a single digit number then add it to list
             * otherwise, add each digit together to obtain a single digit number
             */
            if (tempNumber < 10) {
                tempArray[i] = tempNumber;
            } else {
                tempArray[i] = sumOfDigits(tempNumber);
            }
        }

        // Add the remaining digits
        for (int i = 0; i < listSize; i += 2) {
            tempArray[i] = numberList.get(i);
        }

        // Take sum of all digits
        for (int number : tempArray) {
            sum += number;
        }
        // If total modulo 10 is equal to 0 then the card is valid
        if (sum % 10 == 0) {
            isValid = true;
        }

        return isValid;
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


      public static boolean validateCardExpiryDate(String expiryDate) {
            return expiryDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}");

    }
}
