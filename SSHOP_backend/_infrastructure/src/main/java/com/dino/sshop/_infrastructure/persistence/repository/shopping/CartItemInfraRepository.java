package com.dino.sshop._infrastructure.persistence.repository.shopping;

import com.dino.sshop._domain.inventory.model.entity.Sku;
import com.dino.sshop._domain.shopping.model.entity.Cart;
import com.dino.sshop._domain.shopping.model.entity.CartItem;
import com.dino.sshop._domain.shopping.repository.ICartItemDomainRepository;
import com.dino.sshop._infrastructure.persistence.jpa.shopping.ICartItemJpaMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartItemInfraRepository implements ICartItemDomainRepository {

    ICartItemJpaMapper cartItemJpaMapper;

    @Override
    public Optional<CartItem> findBySkuAndCart(Sku sku, Cart cart) {
        return this.cartItemJpaMapper.findBySkuAndCart(sku, cart);
    }

    @Override
    public Optional<CartItem> findById(String id) {
        return this.cartItemJpaMapper.findById(id);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return this.cartItemJpaMapper.save(cartItem);
    }

    @Override
    public void deleteAllById(ArrayList<String> ids) {
        this.cartItemJpaMapper.deleteAllById(ids);
    }
}
