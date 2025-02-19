package com.dino.sshop._infrastructure.persistence.jpa.identity;

import com.dino.sshop._domain.identity.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountJpaMapper extends JpaRepository<Account, String> {
    //FIND//
    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

    //EXIST//
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByUsername(String username);
}
