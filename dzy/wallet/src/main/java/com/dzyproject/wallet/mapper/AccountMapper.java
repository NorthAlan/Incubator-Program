package com.dzyproject.wallet.mapper;

import com.dzyproject.wallet.entity.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountMapper {

    @Select("select * from account where user_id=#{id}")
    Account findByUserId(Integer userId);

    @Insert("INSERT INTO account (user_id, balance, create_time, update_time) VALUES (#{userId}, #{balance}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createAccount(Account account);

    @Update("update account set balance = #{balance} where id = #{id}")
    void updateAccount(Account account);

    @Delete("delete from account where user_id = #{userId}")
    void deleteAccountByUserId(Integer userId);
}
