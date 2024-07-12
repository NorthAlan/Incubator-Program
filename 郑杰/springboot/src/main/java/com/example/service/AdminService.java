package com.example.service;

import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 管理员业务处理
 **/
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * 新增
     */

    public void add(Admin admin) {
        // 使用 Optional 处理可能的 null 值
        Optional<Admin> dbAdminOptional = Optional.ofNullable(adminMapper.selectByUsername(admin.getUsername()));

        // 检查用户名是否已经存在
        if (dbAdminOptional.isPresent()) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }

        // 检查密码是否为空
        if (admin.getPassword() == null || admin.getPassword().isEmpty()) {
            admin.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }

        // 检查名字是否为空
        if (admin.getName() == null || admin.getName().isEmpty()) {
            admin.setName(admin.getUsername());
        }

        // 设置角色
        admin.setRole(RoleEnum.ADMIN.name());

        // 插入到数据库
        adminMapper.insert(admin);
    }


    /**
     * 删除
     */
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            adminMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Admin admin) {
        adminMapper.updateById(admin);
    }

    /**
     * 根据ID查询
     */
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Admin> selectAll(Admin admin) {
        return adminMapper.selectAll(admin);
    }

    /**
     * 分页查询
     */
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }

    /**
     * 注册
     */
    public void register(Account account) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(account, admin);
        add(admin);
    }

    /**
     * 修改密码
     */
    public void updatePassword(Account account) {
        // 从数据库中根据用户名查询管理员用户
        Admin dbAdmin = adminMapper.selectByUsername(account.getUsername());

        // 检查管理员用户是否存在
        if (dbAdmin == null) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // 检查旧密码是否正确
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }

        // 更新密码
        dbAdmin.setPassword(account.getNewPassword());
        adminMapper.updateById(dbAdmin);
    }


}