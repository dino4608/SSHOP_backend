package com.dino.sshop._domain.shopping.repository;

import com.dino.sshop._domain.shopping.model.entity.Order;

import java.util.Optional;

public interface IOrderDomainRepository {
    Order save(Order order);

    Optional<Order> findById(String id);
}
