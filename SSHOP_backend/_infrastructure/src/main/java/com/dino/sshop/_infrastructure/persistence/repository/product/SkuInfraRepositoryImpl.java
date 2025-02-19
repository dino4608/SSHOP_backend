package com.dino.sshop._infrastructure.persistence.repository.product;

import com.dino.sshop._domain.inventory.model.entity.Sku;
import com.dino.sshop._domain.product.repository.ISkuDomainRepository;
import com.dino.sshop._infrastructure.persistence.jpa.product.ISkuJpaMapper;
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
public class SkuInfraRepositoryImpl implements ISkuDomainRepository {
    ISkuJpaMapper skuJpaMapper;

    @Override
    public Optional<Sku> findBySkuCode(String skuCode) {
        return this.skuJpaMapper.findBySkuCode(skuCode);
    }

    @Override
    public Optional<Sku> findById(String id) {
        return this.skuJpaMapper.findById(id);
    }
}
