package com.dzyproject.wallet.controller;

import com.dzyproject.wallet.util.Result;
import com.dzyproject.wallet.entity.Transaction;
import com.dzyproject.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountId}")
    public Result<List<Transaction>> getTransactionsByAccountId(@PathVariable Integer accountId) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
        if (transactions != null && !transactions.isEmpty()) {
            return Result.success(transactions);
        } else {
            return Result.error("没有找到交易记录");
        }
    }
}
