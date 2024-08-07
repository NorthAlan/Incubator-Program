package com.dzyproject.wallet.mapper;

import com.dzyproject.wallet.entity.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransactionMapper {
    @Insert("insert into transaction (account_id, amount, type, status, create_time, update_time) " +
            "values (#{accountId}, #{amount}, #{type}, #{status}, #{createTime}, #{updateTime})")
    void insert(Transaction transaction);

    @Select("select * from transaction where account_id = #{accountId} order by create_time desc")
    List<Transaction> selectTransactionsByAccountId(Integer accountId);
}
