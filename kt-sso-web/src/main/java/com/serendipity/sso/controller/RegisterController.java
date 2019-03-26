package com.serendipity.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serendipity.common.utils.E3Result;
import com.serendipity.sso.service.RegisterService;

/**
 * 注册功能Controller
 * 
 * @author gqh
 *
 */

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@RequestMapping("/page/register")
	public String showRegister() {
		return "register";
	}

	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public E3Result checkData(@PathVariable String param, @PathVariable Integer type) {
		E3Result e3Result = registerService.checkData(param, type);
		return e3Result;
	}
}
