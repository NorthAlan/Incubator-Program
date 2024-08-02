package com.xauat.Service.impl;

import com.xauat.Mapper.UserMapper;
import com.xauat.Mapper.WalletMapper;
import com.xauat.Pojo.User;
import com.xauat.Pojo.Wallet;
import com.xauat.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private WalletMapper walletMapper;



    @Override
    public User selectById(Integer id) {
       return userMapper.selectById(id);
    }
    //根据id查询用户

    @Override
    @Transactional
    public void insert(User user){//注册用户并为用户创建钱包
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertAndGetId(user);//注册用户
        int id= user.getId();
        Wallet wallet = new Wallet(null,id,null,LocalDateTime.now());
        walletMapper.CreateWallet(wallet);//为该用户创建钱包
    }

    @Override
    @Transactional
    public void deleteByIds(List<Integer> ids) {
        userMapper.deleteByIds(ids);
        walletMapper.deleteByIds(ids);//在用户注销的时候，钱包也应被删除
    }

    @Override
    public void update(User user) {//更新用户信息
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public User login(User user) {
       return userMapper.getByUsernamePassword(user);
    }



}
