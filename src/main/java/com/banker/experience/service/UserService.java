package com.banker.experience.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banker.experience.dao.UserRepo;
import com.banker.experience.data.User;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PlaidHelperService plaidHelperService;

    public User createNewUser(User user) {
        User dbUser = userRepo.findByEmail(user.getEmail());

        if (dbUser != null) {
            return null;
        }

        return createUser(user);
    }

    public boolean verifyUser(User user) {
        User dbUser = userRepo.findByEmail(user.getEmail());

        if (dbUser != null && user.getPassword().equals(dbUser.getPassword())) {
            return true;
        }

        return false;
    }

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

    public String createLinkToken() {
        return plaidHelperService.createLinkToken();
    }
}
