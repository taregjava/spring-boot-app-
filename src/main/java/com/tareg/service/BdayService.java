package com.tareg.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class BdayService {
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;

    public String setTime(LocalDate birthday) {
        if(birthday == null){
            return null;
        }

        final boolean isToday = birthday.getMonth() == LocalDate.now().getMonth()
                && birthday.getDayOfMonth() ==LocalDate.now().getDayOfMonth();

        if(birthday.isAfter(LocalDate.now())) {
            return "Please insert a valid birthday.";
        } else if (isToday) {
            return "HAPPY BIRTHDAY!";
        } else {
            yearOfBirth = birthday.getYear();
            monthOfBirth = birthday.getMonthValue();
            dayOfBirth = birthday.getDayOfYear();
            return timeRemaining(monthOfBirth, dayOfBirth);
        }
    }

    private String timeRemaining(int month, int day) {
        int currYear = LocalDate.now().getYear();
        int currMonth = LocalDate.now().getMonthValue();
        int currDay = LocalDate.now().getDayOfYear();

        //TIME REMAINING
        int hrsRemaining = 24 - LocalTime.now().getHour() - 1;
        int minsRemaining = 60 - LocalTime.now().getMinute() - 1;
        int secsRemaining = 60 - LocalTime.now().getSecond();
        //Find how many days in the year in order to calculate the number of days left
        final int daysInYear = LocalDate.now().isLeapYear() ? 366 : 365;
        int daysRemaining = dayOfBirth > currDay ?
                dayOfBirth - currDay - 1:
                (daysInYear - currDay) + dayOfBirth - 1;

        final int age = monthOfBirth > currMonth ?
                currYear - yearOfBirth :
                currYear + 1 - yearOfBirth;

        return ("There are " + daysRemaining + " days,\n"
                + hrsRemaining + " hours,\n"
                + minsRemaining + " minutes and\n"
                + secsRemaining + " seconds until\n"
                + "you turn " + age + "!");
    }
}