package com.dmon.sshop._infrastructure.persistence.jpa.identity;

import com.dmon.sshop._domain.identity.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISellerJpaMapper extends JpaRepository<Shop, String> {
}
