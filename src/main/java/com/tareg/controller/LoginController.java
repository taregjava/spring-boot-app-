package com.tareg.controller;

import com.tareg.cto.UserForm;
import com.tareg.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginController {


    public LoginService loginService = new LoginService();

    public String login(UserForm userForm) {
        try {
            if (userForm == null) {
                return "ERROR";
            } else if (loginService.login(userForm)) {
                return "ok";
            } else {
                return "ok";
            }
        } catch (Exception e) {
            return "ERROR";
        }

    }
}
