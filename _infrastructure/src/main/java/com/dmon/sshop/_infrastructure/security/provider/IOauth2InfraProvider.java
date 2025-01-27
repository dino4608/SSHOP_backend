package com.dmon.sshop._infrastructure.security.provider;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._infrastructure.security.model.enums.Oauth2Type;
import com.dmon.sshop._infrastructure.security.model.response.ExchangeTokenRes;

public interface IOauth2InfraProvider {
    //GOOGLE//
    ExchangeTokenRes exchangeToken(String code, Oauth2Type oauth2Type);

    Account getUserInfo(String accessToken, Oauth2Type oauth2Type);
}
