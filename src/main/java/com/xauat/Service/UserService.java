package com.xauat.Service;

import com.xauat.Pojo.User;

import java.util.List;

public interface UserService {
    User selectById(Integer id);


    void insert(User user);

    void deleteByIds(List<Integer> ids);

    void update(User user);

    User login(User user);

}
