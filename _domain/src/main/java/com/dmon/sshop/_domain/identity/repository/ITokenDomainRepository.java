package com.dmon.sshop._domain.identity.repository;

import com.dmon.sshop._domain.identity.model.entity.Token;

import java.util.Optional;

public interface ITokenDomainRepository {

    Token save(Token token);

    Optional<Token> findById(String id);

}
