package com.dmon.sshop._domain.identity.repository;

import java.util.Optional;

import com.dmon.sshop._domain.identity.model.entity.Shop;

public interface ISellerDomainRepository {
    // FIND//
    Optional<Shop> findById(String id);

    // PERSIST//
    Shop save(Shop seller);
}
