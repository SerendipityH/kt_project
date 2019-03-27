package com.serendipity.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.serendipity.mapper.TbItemMapper;
import com.serendipity.pojo.TbItem;
import com.serendipity.pojo.TbItemExample;

public class PageHelperTest {
  @Test
  public void testPageHelper() throws Exception {
    // 初始化spring容器
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
    // 从容器获得Mapper代理对象
    TbItemMapper itemMapper = applicationContext.getBean(TbItemMapper.class);
    PageHelper.startPage(1, 10);
    // 执行查询
    TbItemExample example = new TbItemExample();
    List<TbItem> list = itemMapper.selectByExample(example);
    // 取分页信息，PageInfo.
    PageInfo<TbItem> pageInfo = new PageInfo<>(list);
    System.out.println(pageInfo.getTotal());
    System.out.println(pageInfo.getPages());
    System.out.println(list.size());
  }
}
