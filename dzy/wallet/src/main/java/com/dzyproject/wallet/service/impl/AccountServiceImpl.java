package com.dzyproject.wallet.service.impl;

import com.dzyproject.wallet.entity.Account;
import com.dzyproject.wallet.entity.Transaction;
import com.dzyproject.wallet.mapper.AccountMapper;
import com.dzyproject.wallet.mapper.TransactionMapper;
import com.dzyproject.wallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public Account findByUserId(Integer userId) {
        return accountMapper.findByUserId(userId);
    }

    @Override
    @Transactional
    public void createAccount(Integer userId) {
        Account account = new Account();
        account.setUserId(userId);
        account.setBalance(BigDecimal.ZERO);
        account.setCreateTime(LocalDateTime.now());
        account.setUpdateTime(LocalDateTime.now());
        accountMapper.createAccount(account);
    }

    @Override
    @Transactional
    public void deposit(Integer userId, BigDecimal amount) {
        Account account = accountMapper.findByUserId(userId);
        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
            accountMapper.updateAccount(account);
            createTransaction(userId, amount, "CREDIT");
        }
    }

    @Override
    @Transactional
    public void withdraw(Integer userId, BigDecimal amount) {
        Account account = accountMapper.findByUserId(userId);
        if (account != null) {
            if (account.getBalance().compareTo(amount) >= 0) {
                account.setBalance(account.getBalance().subtract(amount));
                accountMapper.updateAccount(account);
                createTransaction(userId, amount, "DEBIT");
            } else {
                throw new IllegalArgumentException("余额不足");
            }
        }
    }

    @Override
    @Transactional
    public void deleteAccount(Integer userId) {
        accountMapper.deleteAccountByUserId(userId);
    }

    @Override
    public void createTransaction(Integer userId, BigDecimal amount, String type) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(userId);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setStatus("COMPLETED");
        transaction.setCreateTime(LocalDateTime.now());
        transaction.setUpdateTime(LocalDateTime.now());
        transactionMapper.insert(transaction);

    }

    @Override
    @Transactional
    public void transfer(Integer fromUserId, Integer toUserId, BigDecimal amount) {
        // 检查发起方账户余额是否足够
        Account fromAccount = accountMapper.findByUserId(fromUserId);
        if (fromAccount != null && fromAccount.getBalance().compareTo(amount) >= 0) {
            // 检查接收方账户是否存在
            Account toAccount = accountMapper.findByUserId(toUserId);
            if (toAccount == null) {
                throw new IllegalArgumentException("接收账户不存在");
            }
            // 执行转账
            fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
            toAccount.setBalance(toAccount.getBalance().add(amount));
            accountMapper.updateAccount(fromAccount);
            if (toAccount != null) {
                accountMapper.updateAccount(toAccount);
            }
            // 创建交易记录
            createTransaction(fromUserId, amount.negate(), "DEBIT");
            createTransaction(toUserId, amount, "CREDIT");
        } else {
            throw new IllegalArgumentException("余额不足");
        }
    }
}



