package com.dino.sshop._infrastructure.persistence.repository.inventory;

import com.dino.sshop._domain.inventory.model.entity.Inventory;
import com.dino.sshop._domain.inventory.repository.IInventoryDomainRepository;
import com.dino.sshop._infrastructure.persistence.jpa.product.IInventoryJpaMapper;
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
public class InventoryInfraRepository implements IInventoryDomainRepository {

    IInventoryJpaMapper inventoryJpaMapper;

    @Override
    public Optional<Inventory> findById(String id) {
        return this.inventoryJpaMapper.findById(id);
    }
}
