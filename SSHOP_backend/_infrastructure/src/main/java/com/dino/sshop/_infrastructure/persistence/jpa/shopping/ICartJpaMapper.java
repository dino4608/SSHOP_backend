package com.dino.sshop._infrastructure.persistence.jpa.shopping;

import com.dino.sshop._domain.shopping.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartJpaMapper extends JpaRepository<Cart, String> {
}
