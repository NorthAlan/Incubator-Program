package com.dzyproject.wallet.mapper;

import com.dzyproject.wallet.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    @Insert("insert into user(username,password,create_time,update_time) " +
            "value(#{username},#{md5util},now(),now())")
    void add(String username, String md5util);

    @Update("update user set nickname=#{nickname},email=#{email},phone=#{phone},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set password=#{md5String},update_time=now() where id=#{id}")
    void updatePwd(String md5String, Integer id);
}
