package com.banker.experience.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.banker.experience.data.User;
import com.banker.experience.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:19006")
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("signup")
    public @ResponseBody String addNewUser(@RequestBody User user) {
        System.out.println("This is the user that is being saved: " + user);
        userService.createUser(user);
        return "User has been created";
    }

    @GetMapping("all")
    public @ResponseBody String getAllUsers() {
        Iterable<User> users = userService.getAllUsers();
        if (((Collection<User>) users).size() == 0) {
            return "Rip";
        }

        return users.toString();
    }
}
