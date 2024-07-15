package com.example.wallet.domain.exceptions;
//在执行交易前，交易验证失败，比如输入的支付信息不符合要求
public class TransactionValidationException extends RuntimeException {
    public TransactionValidationException(String message) {
        super(message);
    }
}