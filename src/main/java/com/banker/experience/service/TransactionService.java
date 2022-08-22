package com.banker.experience.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Transaction> getById(Integer id) {
        return transactionRepo.findById(id);
    }

    public Iterable<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public void deleteAll() {
        transactionRepo.deleteAll();
    }

    public Transaction verifyTransaction(Integer id, String description, String category) {
        Optional<Transaction> foundTransaction = getById(id);
        if (foundTransaction.isEmpty())
            return null;

        Transaction transaction = foundTransaction.get();
        transaction.setDescription(description);
        transaction.setCategory(category);
        transaction.setVerified(true);

        return updateTransaction(transaction);
    }
}
