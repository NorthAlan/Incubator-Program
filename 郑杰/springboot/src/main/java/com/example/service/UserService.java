package com.example.service;

import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 管理员业务处理
 **/
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 新增
     */


    public void add(User user) {
        // 从数据库中根据用户名查询用户
        User dbUser = userMapper.selectByUsername(user.getUsername());

        // 检查用户是否已经存在
        if (dbUser != null) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }

        // 检查密码是否为空
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }

        // 检查名字是否为空
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getUsername());
        }

        // 设置角色
        user.setRole(RoleEnum.USER.name());

        // 插入到数据库
        userMapper.insert(user);
    }


    /**
     * 删除
     */
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            userMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(User user) {
        userMapper.updateById(user);
    }

    /**
     * 根据ID查询
     */
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    /**
     * 分页查询
     */
    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        return PageInfo.of(list);
    }

    /**
     * 修改密码
     */

    public void updatePassword(Account account) {
        // 从数据库中根据用户名查询用户
        User dbUser = userMapper.selectByUsername(account.getUsername());

        // 检查用户是否存在
        if (dbUser == null) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // 检查旧密码是否正确
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }

        // 更新密码
        dbUser.setPassword(account.getNewPassword());
        userMapper.updateById(dbUser);
    }


    public BigDecimal charge(Integer id, BigDecimal account) {
        userMapper.charge(id, account);
        User user = this.selectById(id);
        return user.getAccount();
    }
}