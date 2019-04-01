package com.serendipity.cart.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 用户登录处理
 * @author gqh
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        // TODO Auto-generated method stub
        //前处理，执行handler之前执行此方法
        //返回true,放行 false 拦截
        //1.从cookie中取token
        
        //2.如果没有token,未登录状态，直接放行
        //3.取到token,需要调用sso系统的服务，根据token取用户信息
        //4.没有取到用户信息。登录过期，直接放行
        //5.取到用户信息，登录状态
        //6.把用户信息放到request,只需要在controller中判断request中是否包含user信息。放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        //handler执行之后，返回ModelAndView之前

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        // TODO Auto-generated method stub
        //完成处理，返回ModelAndView之后
        //可以再次处理异常
    }

}
