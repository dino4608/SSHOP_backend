package com.dmon.sshop._infrastructure.persistence.repository.identity;

import com.dmon.sshop._domain.identity.model.entity.Shop;
import com.dmon.sshop._domain.identity.repository.ISellerDomainRepository;
import com.dmon.sshop._infrastructure.persistence.jpa.identity.ISellerJpaMapper;
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
public class SellerInfraRepositoryImpl implements ISellerDomainRepository {
    ISellerJpaMapper sellerJpaMapper;

    @Override
    public Optional<Shop> findById(String id) {
        return this.sellerJpaMapper.findById(id);
    }

    @Override
    public Shop save(Shop seller) {
        return this.sellerJpaMapper.save(seller);
    }
}
