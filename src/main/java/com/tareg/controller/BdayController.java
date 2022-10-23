package com.tareg.controller;

import com.tareg.service.BdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class BdayController {

    @Autowired
    private BdayService bdayService;

    @PostMapping("/findBirthDay")
    public String findBirthDay(@RequestParam("bday") String bday){
        //bday format => dd-mm-yyyy
        int year = 0;
        int month = 0;
        int day = 0;

        if(bday.contains("-")){
            String[] bdayArray = bday.split("-");

            year = Integer.parseInt(bdayArray[2]);
            month = Integer.parseInt(bdayArray[1]);
            day = Integer.parseInt(bdayArray[0]);

            return bdayService.setTime(LocalDate.of(year, month, day));
        }else{
            return "Given date format is not invalid!";
        }
    }
}
