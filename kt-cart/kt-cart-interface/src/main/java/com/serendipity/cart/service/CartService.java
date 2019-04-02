package com.serendipity.cart.service;

import com.serendipity.common.utils.E3Result;

public interface CartService {
    
    E3Result addCart(long userId,long itemId,int num);
}
