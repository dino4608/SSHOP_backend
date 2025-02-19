package com.dino.sshop._domain.identity.service;

import java.time.Instant;

public interface ITokenDomainService {
    //UPDATE//
    void updateRefreshToken(String refreshToken, Instant refreshExpDate, String accountId);


}
