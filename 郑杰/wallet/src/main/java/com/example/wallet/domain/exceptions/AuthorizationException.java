package com.example.wallet.domain.exceptions;
//用户尝试执行他们没有权限的操作
public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }
}