package com.dzyproject.wallet.service;

import com.dzyproject.wallet.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByAccountId(Integer accountId);
}
