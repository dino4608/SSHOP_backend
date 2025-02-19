package com.dino.sshop._infrastructure.persistence.jpa.product;

import com.dino.sshop._domain.inventory.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventoryJpaMapper extends JpaRepository<Inventory, String> {
}
