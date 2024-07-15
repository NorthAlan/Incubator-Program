package com.example.wallet.domain.models;
// User.java - 领域模型

import com.example.wallet.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users") // 指定表名
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    private String status;
//
//    @Column(name = "last_login")
//    private LocalDateTime lastLogin;

    /**
     * 更新用户电子邮件。
     * @param newEmail 新的电子邮件地址
     */
    public void updateEmail(String newEmail) {
        // 可以添加更新电子邮件的逻辑，例如验证电子邮件格式
        setEmail(newEmail);
    }

    /**
     * 更新用户密码。
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    public void updatePassword(String oldPassword, String newPassword) {
        // 这里可以添加密码更新逻辑，例如验证旧密码
        // 假设有一个方法来验证当前密码是否正确
        if (verifyPassword(oldPassword)) {
            setPassword(newPassword);
        } else {
            throw new IllegalArgumentException("旧密码不正确");
        }
    }

    /**
     * 验证密码是否正确。
     * @param password 要验证的密码
     * @return 密码是否正确
     */
    private boolean verifyPassword(String password) {
            String query = "SELECT hashed_password FROM wallet_user WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("hashed_password");
                return BCrypt.checkpw(password, hashedPassword);
            }
        return false;
    }


    /**
     * 设置用户的电话号码。
     * @param phoneNumber 电话号码
     */
    public void setPhoneNumber(String phoneNumber) {
        // 可以添加电话号码验证逻辑
        // 假设验证通过
        this.phoneNumber = phoneNumber;
    }

    /**
     * 激活用户账户。
     */
    public void activateAccount() {
        // 激活账户的逻辑
        setStatus(Status.ACTIVE);
    }

    /**
     * 禁用用户账户。
     */
    public void deactivateAccount() {
        // 禁用账户的逻辑
        setStatus(Status.DISABLED);
    }




}