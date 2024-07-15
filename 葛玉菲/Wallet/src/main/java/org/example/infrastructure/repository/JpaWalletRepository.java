package org.example.infrastructure.repository;

import org.example.domain.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaWalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByUserId(Long userId);
}

