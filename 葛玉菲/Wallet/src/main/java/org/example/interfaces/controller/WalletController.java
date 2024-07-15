package org.example.interfaces.controller;

import org.example.application.service.TransactionService;
import org.example.application.service.UserService;
import org.example.application.service.WalletService;
import org.example.domain.model.Transaction;
import org.example.domain.model.Wallet;
import org.example.domain.model.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        return userService.getUserById(userId)
                .map(user -> ResponseEntity.ok(walletService.createWallet(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Wallet>> getWalletsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(walletService.getWalletsByUserId(userId));
    }

    @PostMapping("/{walletId}/deposit")
    public ResponseEntity<Transaction> deposit(@PathVariable Long walletId, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return ResponseEntity.ok(transactionService.createTransaction(walletId, amount, TransactionType.DEPOSIT));
    }

    @PostMapping("/{walletId}/payment")
    public ResponseEntity<Transaction> makePayment(@PathVariable Long walletId, @RequestBody Map<String, Double> request) {
//        Double amount
        //return null;
        Double amount = request.get("amount");
        return ResponseEntity.ok(transactionService.createTransaction(walletId, amount, TransactionType.PAYMENT));
    }
}

