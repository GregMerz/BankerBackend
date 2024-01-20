package com.banker.experience.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banker.experience.dao.TransactionRepo;
import com.banker.experience.dao.UserRepo;
import com.banker.experience.data.Transaction;
import com.banker.experience.data.User;

@Service
public class TransactionService {

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    UserRepo userRepo;

    public Transaction createTransaction(Transaction transaction, Integer userId) {
        User user = userRepo.getReferenceById(userId);
        transaction.setUser(user);

        return transactionRepo.save(transaction);
    }

    public Transaction getTransactionById(Integer id) {
        return transactionRepo.findById(id).get();
    }

    public Iterable<Transaction> getAllTransactions(Integer userId) {
        User user = userRepo.getReferenceById(userId);

        return transactionRepo.findByUser(user);
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
