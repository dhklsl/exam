package com.lsl.exam.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lsl.exam.entity.TabUser;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final long EXPIRE_TIME = 1000 * 60 * 60 *24;

    //设置私钥
    private static final String TOKEN_SECRET = "aa082c-66rt89-29sr3t-y9t7b8";


    /**
     * 创建携带自定义信息和声明的自定义私钥的jwt
     * @param user  用户信息表
     * @return  jwt串
     */
    public static String creatJwt(TabUser user){
        //构建头部信息
        Map<String,Object> header = new HashMap<>();
        header.put("typ","JWT");
        header.put("alg","HS256");

        //根据私钥构建密钥信息
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

        //根据当前用户密码构建密钥信息
//        Algorithm algorithm = Algorithm.HMAC256(user.getUserpwd());

        //设置过期时间为当前时间一天后
        Date nowDate = new Date();
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);

        String jwt = JWT.create().withHeader(header)
                .withClaim("account",user.getAccount())//业务信息:员工号
                .withClaim("username",user.getUsername())//业务信息:员工姓名
                .withClaim("rolename",user.getRoleName())//业务信息:角色
                .withIssuer("SERVICE")//声明,签名是有谁生成 例如 服务器
                .withNotBefore(new Date())//声明,定义在什么时间之前，该jwt都是不可用的
                .withExpiresAt(expireDate)//声明, 签名过期的时间
                .sign(algorithm);//根据algorithm生成签名

        return jwt;

    }
}
