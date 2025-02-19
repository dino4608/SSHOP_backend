package com.dino.sshop._infrastructure.persistence.repository.shopping;

import com.dino.sshop._domain.shopping.model.entity.Order;
import com.dino.sshop._domain.shopping.repository.IOrderDomainRepository;
import com.dino.sshop._infrastructure.persistence.jpa.shopping.IOrderJpaMapper;
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
public class OrderInfraRepository implements IOrderDomainRepository {

    IOrderJpaMapper orderJpaMapper;

    @Override
    public Order save(Order order) {
        return this.orderJpaMapper.save(order);
    }

    @Override
    public Optional<Order> findById(String id) {
        return this.orderJpaMapper.findById(id);
    }
}
