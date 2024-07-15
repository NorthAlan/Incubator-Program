package com.example.wallet.domain.exceptions;
//交易请求的参数无效或不符合预期格式
public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message) {
        super(message);
    }
}