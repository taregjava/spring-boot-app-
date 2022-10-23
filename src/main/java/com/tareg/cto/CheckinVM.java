package com.tareg.cto;
import com.tareg.entity.Checkin;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CheckinVM {

    private long id;
    private List<GuestVM> guests;

    public CheckinVM(Checkin checkin) {
        this.id = checkin.getId();
        this.guests = checkin.getGuests().stream().map(GuestVM::new).collect(Collectors.toList());
    }
}
