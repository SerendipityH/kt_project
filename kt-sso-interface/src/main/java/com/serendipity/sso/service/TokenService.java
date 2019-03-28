package com.serendipity.sso.service;
/**
 * 根据token查询用户信息
 * 
 * @author gqh
 *
 */

import com.serendipity.common.utils.E3Result;

public interface TokenService {
    E3Result getUserByToken(String token);
}
