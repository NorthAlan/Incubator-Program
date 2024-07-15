package org.example.domain.service;

import org.example.domain.model.Wallet;
import org.example.domain.model.enums.TransactionType;
import org.example.domain.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletDomainService {
    @Autowired
    private WalletRepository walletRepository;

    public Optional<Wallet> updateBalance(Long walletId, Double amount, TransactionType type) {
        Optional<Wallet> walletOpt = walletRepository.findById(walletId);
        if (walletOpt.isPresent()) {
            Wallet wallet = walletOpt.get();
            if (type == TransactionType.DEPOSIT) {
                wallet.setBalance(wallet.getBalance() + amount);
            } else if (type == TransactionType.PAYMENT || type == TransactionType.TRANSFER) {
                wallet.setBalance(wallet.getBalance() - amount);
            }
            walletRepository.save(wallet);
        }
        return walletOpt;
    }

    public Optional<Wallet> findById(Long walletId) {
        return walletRepository.findById(walletId);
    }
}
