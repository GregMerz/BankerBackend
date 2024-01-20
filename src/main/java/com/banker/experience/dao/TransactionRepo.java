package com.banker.experience.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banker.experience.data.Transaction;
import com.banker.experience.data.User;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

    public List<Transaction> findByUser(User user);
}
