package com.dzyproject.wallet.controller;

import com.dzyproject.wallet.util.Result;
import com.dzyproject.wallet.entity.User;
import com.dzyproject.wallet.service.UserService;
import com.dzyproject.wallet.util.JwtUtil;
import com.dzyproject.wallet.util.Md5Util;
import com.dzyproject.wallet.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        User user = userService.findByUsername(username);
        if (user==null){
            userService.register(username,password);
            return Result.success();
        }else {
            return Result.error("用户名已被占用！");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User loginUser = userService.findByUsername(username);
        if (loginUser==null){
            return Result.error("用户名错误");
        }
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            Map<String,Object> wallet = new HashMap<>();
            wallet.put("id",loginUser.getId());
            wallet.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(wallet);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userinfo")
    public Result<User> userinfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params){
        String oldPwd = params.get("原密码");
        String newPwd = params.get("新密码");
        String rePwd = params.get("确认密码");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }

        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUsername(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密码填写不正确");
        }

        if (!rePwd.equals(newPwd)){
            return Result.error("两次输入的新密码不一样");
        }

        userService.updatePwd(newPwd);
        return Result.success();
    }
}
