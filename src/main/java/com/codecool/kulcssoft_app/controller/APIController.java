package com.codecool.kulcssoft_app.controller;

import com.codecool.kulcssoft_app.entity.User;
import com.codecool.kulcssoft_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class APIController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/api/allusers")
    public List<User> getAllUsers(@RequestBody String adminEmail) {
        return userRepository.findAllByAdminEmail(adminEmail);
    }

    @DeleteMapping("/api/{id}")
    @Transactional
    public boolean deleteUser(@PathVariable(name = "id") Integer id){
        User user = userRepository.findUSerById(id);
        if(user == null) {
            return false;
        }
        userRepository.deleteUserById(id);
        return true;
    }
}
