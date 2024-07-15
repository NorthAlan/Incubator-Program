package org.example.application.service;

import org.example.domain.model.User;
import org.example.domain.model.Wallet;
import org.example.domain.model.enums.TransactionType;
import org.example.domain.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    public Wallet createWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(0.0);
        return walletRepository.save(wallet);
    }

    public List<Wallet> getWalletsByUserId(Long userId) {
        return walletRepository.findByUserId(userId);
    }

    public Wallet updateBalance(Long walletId, Double amount, TransactionType type) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        if (type == TransactionType.DEPOSIT) {
            wallet.setBalance(wallet.getBalance() + amount);
        } else if (type == TransactionType.PAYMENT || type == TransactionType.TRANSFER) {
            wallet.setBalance(wallet.getBalance() - amount);
        }
        return walletRepository.save(wallet);
    }
}

