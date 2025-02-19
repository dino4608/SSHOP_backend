package com.dino.sshop._infrastructure.persistence.jpa.identity;

import com.dino.sshop._domain.identity.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenJpaMapper extends JpaRepository<Token, String> {
}
