package com.dzyproject.wallet.service.impl;

import com.dzyproject.wallet.entity.User;
import com.dzyproject.wallet.mapper.UserMapper;
import com.dzyproject.wallet.service.UserService;
import com.dzyproject.wallet.util.Md5Util;
import com.dzyproject.wallet.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user;
    }

    @Override
    public void register(String username, String password) {
        String md5util = Md5Util.getMD5String(password);
        userMapper.add(username,md5util);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void
    updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
