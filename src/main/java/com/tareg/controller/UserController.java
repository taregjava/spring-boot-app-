package com.tareg.controller;

import com.tareg.cto.User;
import com.tareg.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  /*  @Autowired
    private UserRepository userRepository;*/

    @PostMapping("/register")
    public void saveUsers(@RequestBody List<User> users) {

        List<UserEntity> userEntities = new ArrayList<>();

        for (User user : users) {
            userEntities.add(user.dtoToEntity(user));
        }
       // userRepository.saveAll(userEntities);
        // Iterable<UserEntity> persistedUser = userRepository.saveAll(userEntities);
    }

}
