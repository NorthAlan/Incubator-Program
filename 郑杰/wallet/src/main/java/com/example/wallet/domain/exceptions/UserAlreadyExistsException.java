package com.example.wallet.domain.exceptions;
//注册新用户时，数据库中已存在相同用户名或邮箱的用户
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}