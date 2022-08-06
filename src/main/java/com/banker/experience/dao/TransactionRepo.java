package com.banker.experience.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.banker.experience.data.Transaction;

public interface TransactionRepo extends CrudRepository<Transaction, Integer> {
}
