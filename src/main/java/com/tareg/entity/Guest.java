package com.tareg.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guest_id;

    private String name;

    private String mobile_no;

    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_checkin_id", nullable = false )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Checkin checkin;

}