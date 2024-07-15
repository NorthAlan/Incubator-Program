package com.example.wallet.domain.exceptions;
//尝试访问或操作一个不存在的账户时
public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message) {
        super(message);
    }
}
