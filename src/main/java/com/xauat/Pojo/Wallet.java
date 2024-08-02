package com.xauat.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    private Integer walletId;
    private Integer userId;
    private BigDecimal balance;
    private LocalDateTime createTime;
}
