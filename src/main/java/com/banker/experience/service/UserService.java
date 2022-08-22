package com.banker.experience.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banker.experience.dao.UserRepo;
import com.banker.experience.data.User;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(Integer id, User user) {
        return userRepo.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Integer id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty())
            return null;

        return user.get();
    }
}
