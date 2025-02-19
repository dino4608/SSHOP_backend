package com.dino.sshop._infrastructure.persistence.repository.identity;

import com.dino.sshop._domain.identity.model.entity.Token;
import com.dino.sshop._domain.identity.repository.ITokenDomainRepository;
import com.dino.sshop._infrastructure.persistence.jpa.identity.ITokenJpaMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TokenInfraRepository implements ITokenDomainRepository {

    ITokenJpaMapper tokenJpaMapper;

    @Override
    public Token save(Token token) {
        return this.tokenJpaMapper.save(token);
    }

    @Override
    public Optional<Token> findById(String id) {
        return this.tokenJpaMapper.findById(id);
    }
}
