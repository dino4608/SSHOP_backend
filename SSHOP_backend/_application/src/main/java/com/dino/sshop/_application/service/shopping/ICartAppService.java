package com.dino.sshop._application.service.shopping;

import com.dino.sshop._domain.shopping.model.entity.CartItem;
import com.dino.sshop._domain.shopping.model.request.CartItemAddReq;
import com.dino.sshop._domain.shopping.model.request.CartItemEditReq;

public interface ICartAppService {
    //CREATE//
    CartItem addToCart(CartItemAddReq cartItemAddReq);

    //READ//
    Object get();

    //UPDATE//
    CartItem editQuantity(CartItemEditReq cartItemEditReq);

    //DELETE//
    Object removeFromCart(String cartItemIds);
}
