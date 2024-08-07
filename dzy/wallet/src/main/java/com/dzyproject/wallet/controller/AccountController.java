package com.dzyproject.wallet.controller;

import com.dzyproject.wallet.entity.Account;
import com.dzyproject.wallet.util.Result;
import com.dzyproject.wallet.service.AccountService;
import com.dzyproject.wallet.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/balance")
    public Result<BigDecimal> getBalance(){
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Account account = accountService.findByUserId(userId);
        if (account != null) {
            return Result.success(account.getBalance());
        }
        return Result.error("账户不存在");
    }

    @PostMapping("/create")
    public Result createAccount() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Account account = accountService.findByUserId(userId);
        if (account != null) {
            return Result.error("账户已存在");
        }
        accountService.createAccount(userId);
        return Result.success();
    }

    @PostMapping("/deposit")
    public Result deposit(@RequestParam BigDecimal amount) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        accountService.deposit(userId, amount);
        return Result.success();
    }

    @PostMapping("/withdraw")
    public Result withdraw(@RequestParam BigDecimal amount) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        try {
            accountService.withdraw(userId, amount);
            return Result.success();
        } catch (IllegalArgumentException e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/transfer")
    public Result transfer(@RequestParam Integer toUserId, @RequestParam BigDecimal amount) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        try {
            accountService.transfer(userId, toUserId, amount);
            return Result.success();
        } catch (IllegalArgumentException e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/details")
    public Result<Account> getAccountDetails() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Account account = accountService.findByUserId(userId);
        if (account != null) {
            return Result.success(account);
        }
        return Result.error("账户不存在");
    }

    @DeleteMapping("/delete")
    public Result deleteAccount() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Account account = accountService.findByUserId(userId);
        if (account == null) {
            return Result.error("账户不存在");
        }
        accountService.deleteAccount(userId);
        return Result.success("账户已成功删除");
    }
}
