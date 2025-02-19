package com.dino.sshop._domain.shopping.repository;

import com.dino.sshop._domain.inventory.model.entity.Sku;
import com.dino.sshop._domain.shopping.model.entity.Cart;
import com.dino.sshop._domain.shopping.model.entity.CartItem;

import java.util.ArrayList;
import java.util.Optional;

public interface ICartItemDomainRepository {
    Optional<CartItem> findBySkuAndCart(Sku sku, Cart cart);

    Optional<CartItem> findById(String id);

    CartItem save(CartItem cartItem);

    void deleteAllById(ArrayList<String> ids);

}
