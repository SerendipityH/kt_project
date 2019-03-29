package com.serendipity.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.serendipity.common.jedis.JedisClient;
import com.serendipity.common.utils.E3Result;
import com.serendipity.common.utils.JsonUtils;
import com.serendipity.pojo.TbUser;
import com.serendipity.sso.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JedisClient jedisClient;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public E3Result getUserByToken(String token) {
        // TODO Auto-generated method stub
        // 根据token到redis中取用户信息
        String json = jedisClient.get("SESSION:" + token);
        // 取不到用户信息，登录已经过期，返回登录过期
        if (StringUtils.isBlank(json)) {
            return E3Result.build(201, "用户登录已经过期");
        }
        // 取到用户信息更新token的过期时间
        jedisClient.expire("SESSION:" + token, SESSION_EXPIRE);
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        // 返回结果
        return E3Result.ok(user);
    }

}
