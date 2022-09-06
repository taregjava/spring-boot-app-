package com.tareg.repo;

import com.tareg.cto.UserForm;

import java.util.HashMap;
import java.util.Map;

public class LoginRepository {

    Map<String,String> users;

    public LoginRepository(){
        users= new HashMap<>();
        users.put("user1","p1");
        users.put("user2","p3");
        users.put("user3","p4");

    }
    public boolean login(UserForm userForm){
        System.out.println("login repo "+userForm);
        String username= userForm.getUsername();
        String password= userForm.getPassword();
        return users.keySet().contains(username)
                && users.get(username).equals(password);
    }
}
