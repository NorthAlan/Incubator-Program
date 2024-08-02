package com.xauat.Service;

import com.xauat.Pojo.User;
import com.xauat.Pojo.Wallet;

import java.math.BigDecimal;
import java.util.List;

public interface WalletService {
     Wallet getWalletByUserId(Integer id);

     void addBalance(Integer id,BigDecimal balance);

     void subBalance(Integer id,BigDecimal money);

     void transfer(Integer id,String username,BigDecimal money);

     void deleteByIds(List<Integer> ids);
}
