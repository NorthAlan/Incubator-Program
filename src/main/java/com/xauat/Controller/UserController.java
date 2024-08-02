package com.xauat.Controller;

import com.xauat.Pojo.Result;
import com.xauat.Pojo.User;
import com.xauat.Service.UserService;
import com.xauat.anno.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Log
    @PostMapping("/Users")//添加用户
    public Result add(@RequestBody User user){
        userService.insert(user);
        return Result.success();
    }
    @Log
    @DeleteMapping("/Users/{ids}")//用户注销
    public Result delete(@PathVariable List<Integer> ids){
        userService.deleteByIds(ids);
        return Result.success();
    }

    @GetMapping("/Users/{id}")//根据id查询用户
    public Result SelectById(@PathVariable Integer id){
        User user =  userService.selectById(id);
        return Result.success(user);
    }
    @Log
    @PutMapping("/Users")//更新用户
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }


}
