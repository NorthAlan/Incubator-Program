package com.xauat.Controller;

import com.xauat.Pojo.Result;
import com.xauat.Pojo.User;
import com.xauat.Service.UserService;
import com.xauat.Utils.JwtUtils;
import com.xauat.anno.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class loginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user){//传入用户名和密码
      User U = userService.login(user);//判断用户名和密码是否正确
        if (U!=null){//用户名和密码正确
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",U.getId());
            claims.put("name",U.getName());
            claims.put("username",U.getUsername());//将该用户名和密码对应的用户的id、name、username存入JWT令牌
            String jwt = JwtUtils.generateJwt(claims);//生成JWT令牌
            return Result.success(jwt);//返回用户名密码正确的信息，和jwt
        }
        else {
            return Result.error("用户名或密码错误");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        userService.insert(user);//注册用户并为用户创建了钱包
        return Result.success();
    }
}
