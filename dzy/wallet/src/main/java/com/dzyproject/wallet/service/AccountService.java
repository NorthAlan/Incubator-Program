package com.dzyproject.wallet.service;

import com.dzyproject.wallet.entity.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account findByUserId(Integer userId);

    void createAccount(Integer userId);

    void deposit(Integer userId, BigDecimal amount);

    void withdraw(Integer userId, BigDecimal amount);

    void deleteAccount(Integer userId);

    void createTransaction(Integer userId, BigDecimal amount, String type);

    void transfer(Integer fromUserId, Integer toUserId, BigDecimal amount);
}
