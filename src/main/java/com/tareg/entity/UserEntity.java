package com.tareg.entity;
import lombok.Data;

import javax.persistence.*;

/*@Entity
@Table(name = "tbl_user")
@Data*/
@Data
public class UserEntity {
  /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)*/
    public int id;
    public String first_name;
    public String last_name;
    public String email;
    public String password;
}