package com.serendipity.pagehelper;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javassist.ClassClassPath;

public class TestPublish {
  @Test
  public void publishService() throws Exception {
    ApplicationContext classPathXmlApplicationContext =
        new ClassPathXmlApplicationContext("classpath:applicationContext-*.xml");

    System.out.println("服务已经启动。。。");
    System.in.read();
    System.out.println("服务已经关闭");
  }
}
