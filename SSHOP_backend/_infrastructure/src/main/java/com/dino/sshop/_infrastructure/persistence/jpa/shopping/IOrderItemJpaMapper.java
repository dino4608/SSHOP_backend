package com.dino.sshop._infrastructure.persistence.jpa.shopping;

import com.dino.sshop._domain.shopping.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemJpaMapper extends JpaRepository<OrderItem, String> {
}
