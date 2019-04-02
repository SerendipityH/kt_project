package com.serendipity.cart.serviceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.serendipity.cart.service.CartService;
import com.serendipity.common.jedis.JedisClient;
import com.serendipity.common.utils.E3Result;
import com.serendipity.common.utils.JsonUtils;
import com.serendipity.mapper.TbItemMapper;
import com.serendipity.pojo.TbItem;
import com.serendipity.pojo.TbUser;

/**
 * 购物车处理
 * 
 * @author gqh
 *
 */
public class CartServiceImpl implements CartService {
    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_CART_PRE}")
    private String REDIS_CART_PRE;

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public E3Result addCart(long userId, long itemId, int num) {
        // TODO Auto-generated method stub
        // 向redis中添加购物车
        // 数据类型是hash key: 用户id filed:商品id value :用户信息
        // 判断商品是否存在
        Boolean hexists = jedisClient.hexists(REDIS_CART_PRE + ":" + userId, itemId + "");
        // 如果存在数量相加
        if (hexists) {
            String json = jedisClient.hget(REDIS_CART_PRE + ":" + userId, itemId + "");
            // 把json转换成TbItem
            TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
            item.setNum(item.getNum() + num);
            // 写回redis
            jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "",
                    JsonUtils.objectToJson(item));
            return E3Result.ok();
        }
        // 如果不存在，根据商品id取商品信息
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        // 设置购物车数量
        item.setNum(num);
        // 取一张图片
        String image = item.getImage();
        if (StringUtils.isNotBlank(image)) {
            item.setImage(image.split(",")[0]);
        }
        // 添加到购物车
        jedisClient.hset(REDIS_CART_PRE + ":" + userId, itemId + "", JsonUtils.objectToJson(item));
        return E3Result.ok();
    }

}
