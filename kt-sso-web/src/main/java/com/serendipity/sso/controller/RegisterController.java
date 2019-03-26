package com.serendipity.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册功能Controller
 * @author gqh
 *
 */

@Controller
public class RegisterController {
	@RequestMapping("/page/register")
	public String showRegister() {
		return "register";
	}
}
