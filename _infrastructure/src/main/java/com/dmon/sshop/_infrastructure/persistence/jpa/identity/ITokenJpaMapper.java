package com.dmon.sshop._infrastructure.persistence.jpa.identity;

import com.dmon.sshop._domain.identity.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenJpaMapper extends JpaRepository<Token, String> {
}
