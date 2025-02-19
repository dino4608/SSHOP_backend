package com.dino.sshop._infrastructure.persistence.repository.identity;

import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.identity.repository.IShopDomainRepository;
import com.dino.sshop._infrastructure.persistence.jpa.identity.IShopJpaMapper;
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
public class ShopInfraRepositoryImpl implements IShopDomainRepository {

    IShopJpaMapper shopJpaMapper;

    @Override
    public Optional<Shop> findById(String id) {
        return this.shopJpaMapper.findById(id);
    }

    @Override
    public Shop save(Shop seller) {
        return this.shopJpaMapper.save(seller);
    }
}
