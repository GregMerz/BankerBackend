package com.banker.experience.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banker.experience.data.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
}
