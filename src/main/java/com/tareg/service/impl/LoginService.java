package com.tareg.service.impl;

import com.tareg.cto.UserForm;
import com.tareg.repo.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class LoginService {
    @Autowired
    private LoginRepository loginRepository;
    private List<String> userLogged= new ArrayList<>();

    public boolean login(UserForm userForm) throws LoginException {
        System.out.println("login service "+userForm);
        checkForm(userForm);
        String username= userForm.getUsername();
        if (userLogged.contains(username)){
            throw new LoginException(username +" already logged");
        }
        boolean login = loginRepository.login(userForm);
        if (login){
            userLogged.add(username);
        }
        return login;
    }
    private void checkForm(UserForm userForm){
        assert userForm != null;
       assert  userForm.getUsername() != null;
       assert userForm.getPassword() != null;

    }

}
