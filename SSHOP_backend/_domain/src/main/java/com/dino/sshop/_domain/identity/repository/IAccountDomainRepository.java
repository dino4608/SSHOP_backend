package com.dino.sshop._domain.identity.repository;

import com.dino.sshop._domain.identity.model.entity.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountDomainRepository {
    //READ//
    Optional<Account> findById(String id);

    Optional<Account> findByUsername(String username);

    Optional<Account> findByEmail(String email);

    List<Account> findAll();

    //WRITE//
    Account save(Account account);

    void deleteById(String id);

    //OTHERS//
    void flush();
}
