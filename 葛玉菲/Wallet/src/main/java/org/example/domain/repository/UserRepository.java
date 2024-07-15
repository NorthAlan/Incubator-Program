package org.example.domain.repository;

import org.example.domain.model.Transaction;
import org.example.domain.model.User;
import org.example.domain.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //List<Transaction> findByWalletId(Long walletId);

    // 根据钱包ID查询拥有该钱包的用户列表
    //List<User> findByWalletId(Long walletId);
    //List<User> findByWallet(Wallet wallet);
}
