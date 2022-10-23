package com.tareg.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Checkin {

    @Id
    private Long id;

    private Integer no_of_guests;

    @OneToMany(mappedBy = "checkin", cascade = CascadeType.ALL)
    private Set<Guest> guests = new HashSet<>();
}