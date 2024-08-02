package com.xauat.Mapper;

import com.xauat.Pojo.Wallet;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface WalletMapper {
    //更新指定id的balance属性
    @Update("update wallets set balance = #{NewBalance} where user_id = #{id}")
    void updateBalance(Integer id, BigDecimal NewBalance);

    //查看对应用户的钱包信息
    @Select("select * from wallets where user_id=#{id}")
    Wallet getWalletByUserId(Integer id);

    //创建钱包
    @Insert("insert into wallets(user_id,create_time) values (#{userId},#{createTime})")
    void CreateWallet(Wallet wallet);

    //删除钱包
    void deleteByIds(List<Integer> ids);
}
