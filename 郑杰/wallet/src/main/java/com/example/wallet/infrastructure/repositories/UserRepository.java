package com.example.wallet.infrastructure.repositories;
// UserRepository.java - 数据访问层

import com.example.wallet.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods here if needed, for example:
    // User findByUsername(String username);
}