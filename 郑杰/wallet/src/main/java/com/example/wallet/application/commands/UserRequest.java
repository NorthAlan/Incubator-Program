package com.example.wallet.application.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// UserRequest.java - 请求处理类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    // Constructors, getters and setters
}