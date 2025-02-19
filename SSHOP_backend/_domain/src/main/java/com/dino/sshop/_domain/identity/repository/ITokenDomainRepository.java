package com.dino.sshop._domain.identity.repository;

import com.dino.sshop._domain.identity.model.entity.Token;

import java.util.Optional;

public interface ITokenDomainRepository {
    //READ//
    Optional<Token> findById(String id);

    //WRITE//
    Token save(Token token);

}
