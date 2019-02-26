package com.codecool.kulcssoft_app.controller;

import com.codecool.kulcssoft_app.entity.User;
import com.codecool.kulcssoft_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/allusers")
    public List<User> getAllUsers(@RequestBody String adminEmail) {
        return userRepository.findAllByAdminEmail(adminEmail);
    }
}
