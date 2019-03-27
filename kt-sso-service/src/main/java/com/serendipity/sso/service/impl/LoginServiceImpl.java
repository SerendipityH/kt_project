package com.serendipity.sso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.serendipity.common.jedis.JedisClient;
import com.serendipity.common.utils.E3Result;
import com.serendipity.mapper.TbUserMapper;
import com.serendipity.pojo.TbUser;
import com.serendipity.pojo.TbUserExample;
import com.serendipity.pojo.TbUserExample.Criteria;
import com.serendipity.sso.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

  @Autowired
  private TbUserMapper userMapper;

  @Autowired
  private JedisClient jedisClient;

  @Override
  public E3Result userLogin(String username, String password) {
    // TODO Auto-generated method stub


    // 1.判断用户名密码是否正确
    TbUserExample example = new TbUserExample();
    Criteria criteria = example.createCriteria();
    criteria.andUsernameEqualTo(username);
    List<TbUser> list = userMapper.selectByExample(example);
    if (list == null || list.size() == 0) {
      // 返回登录失败
      return E3Result.build(400, "用户名或密码错误");
    }
    // 取用户信息
    TbUser user = list.get(0);
    // 判断密码是否正确
    if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
      // 返回登录失败
      return E3Result.build(400, "用户名或密码错误");
    }
    // 2.如果不正确，返回登录失败
    // 3.如果正确生成token
    // 4.把用户信息写入redis,key:token value:用户信息
    // 5.设置Session的过期时间
    // 6.把token返回


    return null;
  }

}
