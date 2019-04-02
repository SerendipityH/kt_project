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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serendipity.cart.service.CartService;
import com.serendipity.common.utils.CookieUtils;
import com.serendipity.common.utils.E3Result;
import com.serendipity.common.utils.JsonUtils;
import com.serendipity.pojo.TbItem;
import com.serendipity.pojo.TbUser;
import com.serendipity.service.ItemService;

/**
 * 购物车处理Controller
 * 
 * @author gqh
 *
 */
@Controller
public class CartController {

    @Autowired
    private ItemService itemService;

    @Value("${COOKIE_CART_EXPIRE}")
    private Integer COOKIE_CART_EXPIRE;

    @Autowired
    private CartService cartService;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num,
            HttpServletRequest request, HttpServletResponse response) {

        // 判断用户是否登录
        TbUser user = (TbUser) request.getAttribute("user");
        // 如果是登录状态
        if (user != null) {
            // 保存到服务端
            cartService.addCart(user.getId(), itemId, num);
            // 返回逻辑视图
            return "cartSuccess";
        }

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
        // 写入cookie
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList),
                COOKIE_CART_EXPIRE, true);
        // 返回成功页面
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

    /**
     * 展示购物车列表
     * 
     * @param request
     * @return
     */
    @RequestMapping("/cart/cart")
    public String showCartList(HttpServletRequest request, HttpServletResponse response) {
        // 从cookie中取购物车列表
        List<TbItem> cartList = getCartListFromCookie(request);
        // 判断用户是否为登录状态
        TbUser user = (TbUser) request.getAttribute("user");
        // 如果是登录状态
        if (user != null) {
            // 从cookie中取购物车列表
            // 如果不为空，把cookie中的购物车商品和服务端的购物车商品合并。
            cartService.mergeCart(user.getId(), cartList);
            // 把cookie中的购物车删除
            CookieUtils.deleteCookie(request, response, "cart");
            // 从服务端取购物车列表
            cartList = cartService.getCartList(user.getId());

        }



        // 未登录状态

        // 把列表传递给页面
        request.setAttribute("cartList", cartList);
        // 返回视图逻辑
        return "cart";
    }

    /**
     * 更新购物车商品数量
     */
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public E3Result updateCartNum(@PathVariable Long itemId, @PathVariable Integer num,
            HttpServletRequest request, HttpServletResponse response) {
        // 判断用户是否为登录状态
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null) {
            cartService.updateCartNum(user.getId(), itemId, num);
            return E3Result.ok();
        }
        // 从cookie中取购物车列表
        List<TbItem> cartList = getCartListFromCookie(request);
        // 遍历商品列表找到对应的商品
        for (TbItem tbItem : cartList) {
            if (tbItem.getId().longValue() == itemId) {
                // 更新数量
                tbItem.setNum(num);
                break;
            }
        }

        // 把购物车列表写回cookie
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList),
                COOKIE_CART_EXPIRE, true);
        // 返回成功
        return E3Result.ok();
    }

    /**
     * 删除购物车商品
     */
    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCartItem(@PathVariable Long itemId, HttpServletRequest request,
            HttpServletResponse response) {
        // 判断用户是否为登录状态
        TbUser user = (TbUser) request.getAttribute("user");
        if (user != null) {
            cartService.deleteCartItem(user.getId(), itemId);
            return "redirect:/cart/cart.html";
        }
        // 从cookie中取购物车列表
        List<TbItem> cartList = getCartListFromCookie(request);
        // 遍历商品列表找到对应的商品
        for (TbItem tbItem : cartList) {
            if (tbItem.getId().longValue() == itemId) {
                // 更新数量
                cartList.remove(tbItem);
                break;
            }
        }
        // 把购物车列表写入cookie
        CookieUtils.setCookie(request, response, "cart", JsonUtils.objectToJson(cartList),
                COOKIE_CART_EXPIRE, true);
        return "redirect:/cart/cart.html";

    }
}
