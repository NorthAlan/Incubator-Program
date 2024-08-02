package com.xauat.Controller;

import com.xauat.Pojo.Result;
import com.xauat.Pojo.Wallet;
import com.xauat.Service.WalletService;
import com.xauat.anno.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/wallets/{id}")//查询
    public Result ReturnWallet(@PathVariable Integer id) {
      Wallet wallet =  walletService.getWalletByUserId(id);
        return Result.success(wallet);
    }

    @Log
    @GetMapping("/wallets/recharge/{id}/{money}")//充值
    public Result recharge(@PathVariable Integer id,@PathVariable BigDecimal money){
        walletService.addBalance(id,money);
        return Result.success();
    }

    @Log
    @GetMapping("/wallets/withdraw/{id}/{money}")//提现
    public Result withDraw(@PathVariable Integer id,@PathVariable BigDecimal money){
        walletService.subBalance(id, money);
        return Result.success();
    }
    @Log
    @GetMapping("/wallets/transfer/{id}/{username}/{money}")//转账
    public Result transfer(@PathVariable Integer id,@PathVariable String username,
                           @PathVariable BigDecimal money){
        walletService.transfer(id,username,money);
        return Result.success();
    }
}
