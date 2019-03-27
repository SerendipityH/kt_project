package com.serendipity.sso.controller;
/**
 * 用户登录处理
 * 
 * @author gqh
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

  @RequestMapping("/page/login")
  public String showLogin() {
    return "login";
  }
}
