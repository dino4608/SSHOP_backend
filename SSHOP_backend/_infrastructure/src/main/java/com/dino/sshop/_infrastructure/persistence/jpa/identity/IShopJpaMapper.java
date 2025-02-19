package com.dino.sshop._infrastructure.persistence.jpa.identity;

import com.dino.sshop._domain.identity.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IShopJpaMapper extends JpaRepository<Shop, String> {
}
