package com.dmon.sshop._infrastructure.security.provider.impl;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._infrastructure.security.httpclient.GoogleTokenClient;
import com.dmon.sshop._infrastructure.security.httpclient.GoogleUserClient;
import com.dmon.sshop._infrastructure.security.model.enums.Oauth2Type;
import com.dmon.sshop._infrastructure.security.model.request.ExchangeTokenRequest;
import com.dmon.sshop._infrastructure.security.model.response.ExchangeTokenResponse;
import com.dmon.sshop._infrastructure.security.model.response.GoogleUserInfoResponse;
import com.dmon.sshop._infrastructure.security.provider.IOauth2InfraProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class Oauth2InfraProviderImpl implements IOauth2InfraProvider {

    GoogleTokenClient googleTokenClient;
    GoogleUserClient googleUserClient;

    @NonFinal
    @Value("${oauth2.google.client-id}")
    protected String CLIENT_ID;

    @NonFinal
    @Value("${oauth2.google.client-secret}")
    protected String CLIENT_SECRET;

    @NonFinal
    @Value("${oauth2.google.redirect-uri}")
    protected String REDIRECT_URI;

    @NonFinal
    @Value("${oauth2.google.grant-type}")
    protected String GRANT_TYPE;

    @Override
    public ExchangeTokenResponse exchangeToken(String code, Oauth2Type oauth2Type) {
        ExchangeTokenResponse exchangeTokenResponse;

        try {
            exchangeTokenResponse = this.googleTokenClient.exchangeToken(ExchangeTokenRequest.builder()
                    .code(code)
                    .clientId(this.CLIENT_ID)
                    .clientSecret(this.CLIENT_SECRET)
                    .redirectUri(this.REDIRECT_URI)
                    .grantType(this.GRANT_TYPE)
                    .build());
        } catch (Exception e) {
            throw new AppException(ErrorCode.OAUTH2__EXCHANGE_TOKEN_FAILED);
        }

        return exchangeTokenResponse;
    }

    @Override
    public Account getUserInfo(String accessToken, Oauth2Type oauth2Type) {
        GoogleUserInfoResponse googleUserInfoResponse;

        try {
            googleUserInfoResponse = this.googleUserClient.getUserInfo("json", accessToken);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(ErrorCode.OAUTH2__GET_USERINFO_FAILED);
        }

        return Account.builder()
                .email(googleUserInfoResponse.getEmail())
                .name(googleUserInfoResponse.getName())
                .build();
    }
}
