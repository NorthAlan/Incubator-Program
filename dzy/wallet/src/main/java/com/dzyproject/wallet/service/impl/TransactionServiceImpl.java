package com.dzyproject.wallet.service.impl;

import com.dzyproject.wallet.entity.Transaction;
import com.dzyproject.wallet.mapper.TransactionMapper;
import com.dzyproject.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;


    @Override
    public List<Transaction> getTransactionsByAccountId(Integer accountId) {
        return transactionMapper.selectTransactionsByAccountId(accountId);
    }
}
