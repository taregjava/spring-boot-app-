package com.tareg.controller;

import com.tareg.entity.Checkin;
import com.tareg.repo.CheckinRepository;
import com.tareg.repo.GuestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkin")
public class CheckinController {

    private final CheckinRepository checkinRepo;
    private final GuestRepository guestRepo;

    public CheckinController(CheckinRepository checkinRepo,GuestRepository guestRepo){
        this.checkinRepo = checkinRepo;
        this.guestRepo = guestRepo;
    }

    @PostMapping("/add")
    ResponseEntity<Object> roomCheckin(@RequestBody Checkin checkin){
        if(checkinRepo.save(checkin) != null){
            return ResponseEntity.accepted().body("Checkin Successfull");
        }
        return ResponseEntity.unprocessableEntity().body("Failed to Checkin");
    }
}