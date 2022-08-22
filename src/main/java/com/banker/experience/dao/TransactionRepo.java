package com.banker.experience.dao;

import org.springframework.data.repository.CrudRepository;
import com.banker.experience.data.Transaction;

public interface TransactionRepo extends CrudRepository<Transaction, Integer> {
}
