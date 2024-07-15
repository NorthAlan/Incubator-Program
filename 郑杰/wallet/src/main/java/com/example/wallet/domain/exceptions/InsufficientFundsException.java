package com.example.wallet.domain.exceptions;
//当用户尝试进行转账或支付，但其账户余额不足时
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}