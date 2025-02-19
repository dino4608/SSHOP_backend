package com.dino.sshop._infrastructure.persistence.repository.shopping;

import com.dino.sshop._domain.shopping.repository.IOrderItemDomainRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderItemInfraRepository implements IOrderItemDomainRepository {
}
