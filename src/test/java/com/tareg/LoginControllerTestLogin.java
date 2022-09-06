package com.tareg;

import com.tareg.controller.LoginController;
import com.tareg.cto.UserForm;
import com.tareg.service.impl.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.LoginException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LoginControllerTestLogin {

    @InjectMocks
    LoginController loginController;
    @Mock
    LoginService loginService;

    UserForm userForm = new UserForm("user1","p1");

    @Test
    void testLoginOk() throws LoginException {
        when(loginService.login(userForm)).thenReturn(true);
        String responseLogin= loginController.login(userForm);

       assertEquals("ok",responseLogin);
        verify(loginService).login(userForm);
        verifyNoInteractions(loginService);
    }
}
