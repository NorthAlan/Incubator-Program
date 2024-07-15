package com.example.wallet.domain.exceptions;
//用户认证失败，如登录时提供的信息不正确
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
}