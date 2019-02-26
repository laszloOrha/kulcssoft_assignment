package com.codecool.kulcssoft_app.controller;

import com.codecool.kulcssoft_app.entity.User;
import com.codecool.kulcssoft_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/allusers")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
