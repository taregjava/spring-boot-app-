package com.tareg.service;

import com.tareg.cto.CheckinVM;
import com.tareg.repo.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckinService {

    private final CheckinRepository checkinRepository;

    public CheckinVM saveCheckin(CheckinVM checkin) {
        return null; // TODO: implement this
    }
}