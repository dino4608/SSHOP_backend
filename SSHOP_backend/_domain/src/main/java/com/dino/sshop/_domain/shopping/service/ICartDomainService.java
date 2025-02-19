package com.dino.sshop._domain.shopping.service;

import com.dino.sshop._domain.shopping.model.entity.Cart;
import com.dino.sshop._domain.shopping.model.entity.CartItem;
import com.dino.sshop._domain.shopping.model.request.CartItemAddReq;
import com.dino.sshop._domain.shopping.model.request.CartItemEditReq;

/**
 * key features:
 * - create a cart by a user
 * - add a product sku by a user
 * - increase quantity of a cart item
 * - reduce quantity of a cart item
 * - remove list of cart items
 * - get a cart by a user
 */
public interface ICartDomainService {
    //CREATE//
    Cart create(String userId);

    CartItem addToCart(CartItemAddReq cartItemAddReq, String userId);

    //READ//
    Object get(String userId);

    //UPDATE//
    CartItem editQuantity(CartItemEditReq cartItemEditReq);

    //DELETE//
    Object removeFromCart(String cartItemIds);

}
