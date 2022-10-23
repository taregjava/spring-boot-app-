package com.tareg.cto;

import com.tareg.entity.UserEntity;
import lombok.Data;

@Data
public class User {
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    public static UserEntity dtoToEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setFirst_name(user.getFirstName());
        userEntity.setLast_name(user.getLastName());
        return userEntity;
    }
}