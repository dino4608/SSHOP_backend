package com.dino.sshop._infrastructure.security.provider;

import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._infrastructure.security.model.enums.TokenType;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Duration;
import java.time.Instant;

public interface ISecurityInfraProvider {
    //PASSWORD//
    String hashPassword(String plain);

    boolean matchPassword(String plain, String hash);

    //JWT//
    String genToken(Account account, TokenType tokenType);

    Jwt decodeToken(String token, TokenType tokenType);

    Duration getValidDuration(TokenType tokenType);

    Instant getExpirationDate(TokenType tokenType);

    String getSecretKey(TokenType tokenType);

    String getAccountId();

}
