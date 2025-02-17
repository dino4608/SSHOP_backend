package com.dmon.sshop._domain.identity.repository;

import com.dmon.sshop._domain.identity.model.entity.Shop;

import java.util.Optional;

public interface IShopDomainRepository {
    //READ//
    Optional<Shop> findById(String id);

    //WRITE//
    Shop save(Shop shop);
}
