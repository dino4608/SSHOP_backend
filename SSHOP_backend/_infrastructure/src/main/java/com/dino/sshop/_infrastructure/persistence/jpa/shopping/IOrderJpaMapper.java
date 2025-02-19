package com.dino.sshop._infrastructure.persistence.jpa.shopping;

import com.dino.sshop._domain.shopping.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderJpaMapper extends JpaRepository<Order, String> {
}
