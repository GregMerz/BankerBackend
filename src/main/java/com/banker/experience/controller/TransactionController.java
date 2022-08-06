package com.banker.experience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.banker.experience.data.Transaction;
import com.banker.experience.service.TransactionService;

@RestController
@RequestMapping("/transactions/")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("add")
    public @ResponseBody String addTransaction(@RequestParam String description, 
                                               @RequestParam String price, 
                                               @RequestParam String category) {

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setPrice(price);
        transaction.setCategory(category);
        System.out.println("This is the transaction: " + transaction);
        transactionService.createTransaction(transaction);
        return "Saved";
    }

    @PostMapping("update")
    public @ResponseBody String updateTransaction(String transactionsJSON) {
        return null;
    }

    @GetMapping("all")
    public @ResponseBody Iterable<Transaction> getAllUsers() {
        // This returns a JSON or XML with the users
        return transactionService.getAllTransactions();
    }
}
