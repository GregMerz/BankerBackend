package com.banker.experience.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banker.experience.dao.TransactionRepo;
import com.banker.experience.data.Transaction;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepo transactionRepo;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public Iterable<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public List<Transaction> updateTransaction(String transactionsJSON) {
        return null;
    }
}
