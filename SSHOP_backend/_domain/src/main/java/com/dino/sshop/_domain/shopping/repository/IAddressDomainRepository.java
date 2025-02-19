package com.dino.sshop._domain.shopping.repository;

import com.dino.sshop._domain.shopping.model.entity.Address;

import java.util.Optional;

public interface IAddressDomainRepository {
    Optional<Address> findById(String id);
}
