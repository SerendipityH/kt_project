package com.serendipity.sso.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serendipity.common.utils.E3Result;
import com.serendipity.mapper.TbUserMapper;
import com.serendipity.pojo.TbUser;
import com.serendipity.pojo.TbUserExample;
import com.serendipity.pojo.TbUserExample.Criteria;
import com.serendipity.sso.service.RegisterService;

/**
 * 用户注册校验
 * 
 * @author gqh
 *
 */
@Service
public class RegisterServiceImpl implements RegisterService {
	@Autowired
	private TbUserMapper userMapper;

	@Override
	public E3Result checkData(String param, int type) {
		// TODO Auto-generated method stub
		// 根据不同的type生成不同的查询条件
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// 1.用户名 2.手机号 3.邮箱
		if (type == 1) {
			criteria.andUsernameEqualTo(param);
		} else if (type == 2) {
			criteria.andPhoneEqualTo(param);
		} else if (type == 3) {
			criteria.andEmailEqualTo(param);
		} else {
			return E3Result.build(400, "数据类型错误");
		}
		// 执行查询
		List<TbUser> list = userMapper.selectByExample(example);
		// 判断结果中是否包含数据
		if (list != null && list.size() > 0) {
			return E3Result.ok(false);
		}
		// 如果没有数据返回true;
		return E3Result.ok(true);
	}

}
