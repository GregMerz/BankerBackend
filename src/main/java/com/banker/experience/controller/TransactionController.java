package com.banker.experience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("addUnverified")
    public @ResponseBody String addTransaction(@RequestBody Transaction transaction) {
        System.out.println("This is the transaction: " + transaction);
        transactionService.createTransaction(transaction);
        return "Saved";
    }

    @PostMapping("update")
    public @ResponseBody Transaction verifyTransaction(@RequestBody Transaction transaction) {
        return transactionService.verifyTransaction(transaction.getId(), transaction.getDescription(),
                transaction.getCategory());
    }

    @GetMapping("all")
    public @ResponseBody Iterable<Transaction> getAllTransaction() {
        return transactionService.getAllTransactions();
    }

    @DeleteMapping("deleteAll")
    public @ResponseBody String deleteAllTransactions() {
        transactionService.deleteAll();
        return "Deleted";
    }
}
