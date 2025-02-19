package com.dino.sshop._domain.shopping.repository;

import com.dino.sshop._domain.shopping.model.entity.Cart;

import java.util.Optional;

public interface ICartDomainRepository {
    Cart save(Cart cart);

    Optional<Cart> findById(String id);
}
