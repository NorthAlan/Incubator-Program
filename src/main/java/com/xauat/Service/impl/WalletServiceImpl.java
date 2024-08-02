package com.xauat.Service.impl;

import com.xauat.Mapper.UserMapper;
import com.xauat.Mapper.WalletMapper;
import com.xauat.Pojo.Wallet;
import com.xauat.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Wallet getWalletByUserId(Integer id) {

       return walletMapper.getWalletByUserId(id);
    }

    @Override
    public void addBalance(Integer id,BigDecimal money) {//给id的钱包充值
        Wallet wallet =  walletMapper.getWalletByUserId(id);//根据用户id获取到钱包
        BigDecimal OldBalance =  wallet.getBalance();//获取到钱包余额
        BigDecimal NewBalance = OldBalance.add(money);//充值后的余额
        walletMapper.updateBalance(id,NewBalance);
    }

    @Override
    public void subBalance(Integer id, BigDecimal money) {//将id的钱包提现
        Wallet wallet =  walletMapper.getWalletByUserId(id);//根据用户id获取到钱包
        BigDecimal OldBalance =  wallet.getBalance();//获取到钱包余额
        BigDecimal NewBalance = OldBalance.subtract(money);//提现后的余额
        walletMapper.updateBalance(id,NewBalance);
    }

    @Transactional
    @Override
    public void transfer(Integer id, String username, BigDecimal money) {//转账
        BigDecimal NewBalanceOfId = walletMapper.getWalletByUserId(id).getBalance().subtract(money);//将id的余额减去money
        Integer id2 = userMapper.GetIDByUsername(username);//根据另一个用户的用户名获取到id
        BigDecimal NewBalanceOfId2 = walletMapper.getWalletByUserId(id2).getBalance().add(money);//给另一个用户的钱包增加money
        walletMapper.updateBalance(id,NewBalanceOfId);
        walletMapper.updateBalance(id2,NewBalanceOfId2);
    }


    @Override
    public void deleteByIds(List<Integer> ids) {
        walletMapper.deleteByIds(ids);
    }


}
