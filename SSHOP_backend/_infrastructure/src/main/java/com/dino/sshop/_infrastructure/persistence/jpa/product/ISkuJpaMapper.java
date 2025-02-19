package com.dino.sshop._infrastructure.persistence.jpa.product;

import com.dino.sshop._domain.inventory.model.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISkuJpaMapper extends JpaRepository<Sku, String> {
    // FIND//
    Optional<Sku> findBySkuCode(String skuCode);
}
