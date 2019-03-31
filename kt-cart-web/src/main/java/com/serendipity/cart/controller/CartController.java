package com.serendipity.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.LineListener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 购物车处理Controller
 * 
 * @author gqh
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.serendipity.common.utils.CookieUtils;
import com.serendipity.common.utils.JsonUtils;
import com.serendipity.pojo.TbItem;
import com.serendipity.service.ItemService;

@Controller
public class CartController {

    @Autowired
    private ItemService itemService;
    
    @Value("${COOKIE_CART_EXPIRE}")
    private Integer COOKIE_CART_EXPIRE;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
            HttpServletRequest request, HttpServletResponse response) {
        // 从cookie中取购物车列表
        List<TbItem> cartList = getCartListFromCookie(request);
        // 判断商品在商品列表中是否存在
        boolean flag = false;
        for (TbItem tbItem : cartList) {
            // 如果存在商品数量增加
            if (tbItem.getId() == itemId.longValue()) {
                flag = true;
                // 找到商品，数量增加
                tbItem.setNum(tbItem.getNum() + num);
                // 跳出循环
                break;
            }
        }
        // 如果不存在，根据商品id查询商品对象，得到一个TbItem对象
        if (!flag) {
            // 根据商品id查询商品信息
            TbItem tbItem = itemService.geTbItemById(itemId);
            tbItem.setNum(num);
            String image = tbItem.getImage();
            if (StringUtils.isNoneBlank(image)) {
                tbItem.setImage(image.split(",")[0]);
            }
            // 把商品添加到商品列表
            cartList.add(tbItem);
        }
        //写入cookie
        CookieUtils.setCookie(request, response,"cart", JsonUtils.objectToJson(cartList),COOKIE_CART_EXPIRE,true);
        //返回成功页面
        return "cartSuccess";
    }

    /**
     * 从cookie中取购物车列表的处理
     * 
     * @param request
     * @return
     */
    private List<TbItem> getCartListFromCookie(HttpServletRequest request) {
        String json = CookieUtils.getCookieValue(request, "cart", true);
        // 判断json是否为空
        if (StringUtils.isBlank(json)) {
            return new ArrayList<TbItem>();
        }
        // 把json转换成商品列表
        List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
        return list;
    }
}
