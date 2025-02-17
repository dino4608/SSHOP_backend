package com.dmon.sshop._infrastructure.security.provider;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._infrastructure.security.model.enums.Oauth2Type;
import com.dmon.sshop._infrastructure.security.model.response.ExchangeTokenResponse;

public interface IOauth2InfraProvider {
    //GOOGLE//
    ExchangeTokenResponse exchangeToken(String code, Oauth2Type oauth2Type);

    Account getUserInfo(String accessToken, Oauth2Type oauth2Type);
}
