package com.xauat.Mapper;

import com.xauat.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    //新增用户信息

    @Select("select id from user where username=#{username}")
    Integer GetIDByUsername(String username);

    void insertAndGetId(User user);

    //批量删除用户信息
    void deleteByIds(List<Integer> ids);

    //根据id查询用户信息
    @Select("select * from user where id = #{id}")
    User selectById(Integer id);


    //修改用户信息
    void update(User user);

    //判断用户名和密码是否在数据库中
    @Select("select * from user where username=#{username} and password=#{password}")
    User getByUsernamePassword(User user);

}
