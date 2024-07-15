package org.example.application.service;

import org.example.domain.model.Transaction;
import org.example.domain.model.Wallet;
import org.example.domain.model.enums.TransactionType;
import org.example.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletService walletService;

    public Transaction createTransaction(Long walletId, Double amount, TransactionType type) {
        Wallet wallet = walletService.updateBalance(walletId, amount, type);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setWallet(wallet);
        transaction.setTimestamp(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByWalletId(Long walletId) {
        return transactionRepository.findByWalletId(walletId);
    }
}

