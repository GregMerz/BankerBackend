package com.banker.experience.dao;

import org.springframework.data.repository.CrudRepository;
import com.banker.experience.data.User;

public interface UserRepo extends CrudRepository<User, Integer> {
}
