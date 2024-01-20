package com.banker.experience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.banker.experience.data.Transaction;
import com.banker.experience.service.TransactionService;

@RestController
@CrossOrigin(origins = "http://localhost:19006")
@RequestMapping("/transactions/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("{id}")
    public Transaction getTransaction(@PathVariable("id") Integer transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @PostMapping("{id}")
    public Transaction updateTransaction(@PathVariable("id") Integer transactionId, Transaction transaction) {
        return transactionService.updateTransaction(transactionId, transaction);
    }

    @PostMapping("addUnverified")
    public @ResponseBody String addTransaction(@RequestBody Transaction transaction, @RequestParam Integer userId) {
        System.out.println("This is the transaction: " + transaction);
        transactionService.createTransaction(transaction, userId);
        return "Saved";
    }

    @PostMapping("update")
    public @ResponseBody Transaction verifyTransaction(@RequestBody Transaction transaction) {
        return transactionService.verifyTransaction(transaction.getId(), transaction.getDescription(),
                transaction.getCategory());
    }

    @GetMapping("all")
    public @ResponseBody Iterable<Transaction> getAllTransaction(@RequestParam Integer userId) {
        return transactionService.getAllTransactions(userId);
    }

    @DeleteMapping("deleteAll")
    public @ResponseBody String deleteAllTransactions() {
        transactionService.deleteAll();
        return "Deleted";
    }
}
