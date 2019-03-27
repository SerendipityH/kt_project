package com.serendipity.sso.controller;
/**
 * 用户登录处理
 * 
 * @author gqh
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serendipity.common.utils.E3Result;


@Controller
public class LoginController {

    @RequestMapping("/page/login")
    public String showLogin() {
        return "login";
    }

    
      @RequestMapping(value="/user/login",method=RequestMethod.POST)
      @ResponseBody 
      public E3Result
     
}
