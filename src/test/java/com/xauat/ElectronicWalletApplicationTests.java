package com.xauat;

import com.xauat.Utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ElectronicWalletApplicationTests {

    @Test
    void contextLoads() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","123");
        claims.put("username","56");//将该用户名和密码对应的用户的id、name、username存入JWT令牌
        String jwt = JwtUtils.generateJwt(claims);//生成JWT令牌
        System.out.println(jwt);
    }

}
