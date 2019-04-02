package com.serendipity.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.serendipity.common.jedis.JedisClient;
import com.serendipity.common.utils.E3Result;
import com.serendipity.common.utils.JsonUtils;
import com.serendipity.content.service.ContentService;
import com.serendipity.mapper.TbContentMapper;
import com.serendipity.pojo.TbContent;
import com.serendipity.pojo.TbContentExample;
import com.serendipity.pojo.TbContentExample.Criteria;

/**
 * 内容管理Service
 * 
 * @author gqh
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("$CONTENT_LIST")
    private String CONTENT_LIST;

    @Override
    public E3Result addContent(TbContent content) {
        // TODO Auto-generated method stub
        content.setCreated(new Date());
        content.setUpdated(new Date());
        // 插入到数据库
        contentMapper.insert(content);
        // 缓存同步
        jedisClient.hdel(CONTENT_LIST, content.getCategoryId().toString());
        return E3Result.ok();
    }

    /**
     * 根据内容分类id查询内容列表
     */
    @Override
    public List<TbContent> getContentListByCid(long cid) {
        // 查询缓存
        try {
            // 如果缓存中有直接响应结果
            String json = jedisClient.hget("CONTENT_LIST", cid + "");
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        // 如果没有查询数据库
        // TODO Auto-generated method stub
        TbContentExample example = new TbContentExample();
        Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        // 把结果添加到缓存
        try {
            jedisClient.hset("CONTENT_LIST", cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

}
