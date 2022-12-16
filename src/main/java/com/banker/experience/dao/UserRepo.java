package com.banker.experience.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banker.experience.data.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
}
