package com.dino.sshop._infrastructure.persistence.repository.shopping;

import com.dino.sshop._domain.shopping.model.entity.Cart;
import com.dino.sshop._domain.shopping.repository.ICartDomainRepository;
import com.dino.sshop._infrastructure.persistence.jpa.shopping.ICartJpaMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartInfraRepository implements ICartDomainRepository {

    ICartJpaMapper cartJpaMapper;

    @Override
    public Cart save(Cart cart) {
        return this.cartJpaMapper.save(cart);
    }

    @Override
    public Optional<Cart> findById(String id) {
        return this.cartJpaMapper.findById(id);
    }
}
