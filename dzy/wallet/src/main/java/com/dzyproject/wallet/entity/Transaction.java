package com.dzyproject.wallet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 交易信息实体类
 */
@Data
public class Transaction {
    @NotNull
    private Integer id;//主键ID
    @NotNull
    private Integer accountId;//关联到account表的账户ID
    private BigDecimal amount;//交易金额
    private String type;//交易类型 DEBIT(扣款)|CREDIT(存款)
    private String status;//交易状态
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//修改时间
}
