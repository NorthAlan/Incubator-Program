package com.example.wallet.domain.exceptions;
//用户尝试使用电子钱包不支持的货币进行交易
public class CurrencyNotSupportedException extends RuntimeException {
    public CurrencyNotSupportedException(String message) {
        super(message);
    }
}