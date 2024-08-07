package com.dzyproject.wallet.service;

import com.dzyproject.wallet.entity.User;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updatePwd(String newPwd);
}
