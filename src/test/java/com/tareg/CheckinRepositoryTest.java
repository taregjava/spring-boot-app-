package com.tareg;
import com.tareg.entity.Checkin;
import com.tareg.entity.Guest;
import com.tareg.repo.CheckinRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CheckinRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    CheckinRepository checkinRepository;

    @Test
    public void test() {

        // create instances
        Checkin checkinInDb = new Checkin();
        Guest guestInDb = new Guest();

        // add relations
        guestInDb.setCheckin(checkinInDb);
        checkinInDb.getGuests().add(guestInDb);

        // save check-in
        checkinRepository.save(checkinInDb);

        // verify that check-in has one guest
        Checkin checkin = testEntityManager.find(Checkin.class, checkinInDb.getId());
        assertThat(checkin.getGuests().size()).isEqualTo(1);

        // verify that guest is connected to a check-in
        Guest guest = testEntityManager.find(Guest.class, guestInDb.getGuest_id());
        assertThat(guest.getCheckin()).isNotNull();
    }
}
