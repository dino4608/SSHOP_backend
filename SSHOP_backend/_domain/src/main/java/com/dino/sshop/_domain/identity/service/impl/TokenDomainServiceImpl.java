package com.dino.sshop._domain.identity.service.impl;

import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.identity.model.entity.Token;
import com.dino.sshop._domain.identity.repository.ITokenDomainRepository;
import com.dino.sshop._domain.identity.service.ITokenDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TokenDomainServiceImpl implements ITokenDomainService {

    ITokenDomainRepository tokenDomainRepo;

    @Override
    public void updateRefreshToken(String refreshToken, Instant refreshExpDate, String accountId) {
        Token token = this.tokenDomainRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.TOKEN__NOT_FOUND));

        token.setRefreshToken(refreshToken);
        token.setRefreshExpDate(refreshExpDate);

        this.tokenDomainRepo.save(token);
    }
}
