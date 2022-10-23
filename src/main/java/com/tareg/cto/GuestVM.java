package com.tareg.cto;

import com.tareg.entity.Guest;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GuestVM {

    private long id;
    private String name;
    private String mobile_no;
    private String address;

    public GuestVM(Guest guest) {
        this.id = guest.getGuest_id();
        this.name = guest.getName();
        this.mobile_no = guest.getMobile_no();
        this.address = guest.getAddress();
    }
}