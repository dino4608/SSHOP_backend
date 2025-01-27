package com.dmon.sshop._domain.identity.repository;

import com.dmon.sshop._domain.identity.model.entity.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountDomainRepository {

    void flush();

    // FIND//
    Optional<Account> findById(String id);

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

    List<Account> findAll();

    // EXIST//
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByUsername(String username);

    // PERSIST//
    Account save(Account account);

    void deleteById(String id);
}
