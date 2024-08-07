package com.dzyproject.wallet.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "dzy_wallet";
	
	//接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> wallet) {
        return JWT.create()
                .withClaim("wallet", wallet)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256(KEY));
    }

	//接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("wallet")
                .asMap();
    }

}
