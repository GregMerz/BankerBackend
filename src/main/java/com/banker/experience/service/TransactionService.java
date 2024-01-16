package com.banker.experience.service;

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

    public Transaction getTransactionById(Integer id) {
        return transactionRepo.findById(id).get();
    }

    public Iterable<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction updateTransaction(Integer transactionId, Transaction transaction) {
        Transaction dbTransaction = transactionRepo.findById(transactionId).get();

        dbTransaction.setCategory(transaction.getCategory());
        dbTransaction.setDescription(transaction.getDescription());
        dbTransaction.setPrice(transaction.getPrice());
        dbTransaction.setVerified(transaction.getVerified());
        dbTransaction.setReceiptUrl(transaction.getReceiptUrl());

        return transactionRepo.save(dbTransaction);
    }

    public Transaction verifyTransaction(Integer id, String description, String category) {
        Transaction dbTransaction = getTransactionById(id);

        dbTransaction.setDescription(description);
        dbTransaction.setCategory(category);
        dbTransaction.setVerified(true);

        return transactionRepo.save(dbTransaction);
    }

    public void deleteAll() {
        transactionRepo.deleteAll();
    }
}
