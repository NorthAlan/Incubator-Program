package com.dzyproject.wallet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *账户信息实体类
 */
@Data
public class Account {
    @NotNull
    private Integer id;//主键ID
    @NotNull
    private Integer userId;//关联到user表的用户ID
    private BigDecimal balance;//余额
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//修改时间
}
