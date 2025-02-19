package com.dino.sshop._application.external.security.impl;

import com.dino.sshop._application.external.security.IAccessSecurityService;
import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.identity.mapper.IAccountMapper;
import com.dino.sshop._domain.identity.mapper.IShopMapper;
import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._domain.identity.model.request.Oauth2LoginRequest;
import com.dino.sshop._domain.identity.model.request.UsernameLoginRequest;
import com.dino.sshop._domain.identity.model.response.AccountInfoResponse;
import com.dino.sshop._domain.identity.model.response.AuthenticationResponse;
import com.dino.sshop._domain.identity.model.response.ShopInfoResponse;
import com.dino.sshop._domain.identity.service.IAccountDomainService;
import com.dino.sshop._domain.identity.service.ITokenDomainService;
import com.dino.sshop._infrastructure.security.model.enums.Oauth2Type;
import com.dino.sshop._infrastructure.security.model.enums.TokenType;
import com.dino.sshop._infrastructure.security.model.response.ExchangeTokenResponse;
import com.dino.sshop._infrastructure.security.provider.IOauth2InfraProvider;
import com.dino.sshop._infrastructure.security.provider.ISecurityInfraProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccessSecurityServiceImpl implements IAccessSecurityService {

    IAccountDomainService accountDomainService;
    ITokenDomainService tokenDomainService;
    ISecurityInfraProvider securityInfraProvider;
    IOauth2InfraProvider oauth2InfraProvider;
    IAccountMapper accountMapper;
    IShopMapper shopMapper;

    //LOGIN//
    @Override
    public AuthenticationResponse loginUsernameForAdmin(UsernameLoginRequest request, HttpHeaders headers) {
        return this.loginUsernameByRole(request, headers, Account.RoleType.ADMIN);
    }

    @Override
    public AuthenticationResponse loginUsernameForSeller(UsernameLoginRequest request, HttpHeaders headers) {
        return this.loginUsernameByRole(request, headers, Account.RoleType.SELLER);
    }

    @Override
    public AuthenticationResponse loginUsernameForBuyer(UsernameLoginRequest request, HttpHeaders headers) {
        return this.loginUsernameByRole(request, headers, Account.RoleType.BUYER);
    }

    @Override
    public AuthenticationResponse loginOauth2ForSeller(Oauth2LoginRequest request, HttpHeaders headers) {
        return this.loginOauth2ByRole(request, headers, Account.RoleType.SELLER);
    }

    @Override
    public AuthenticationResponse loginOauth2ForBuyer(Oauth2LoginRequest request, HttpHeaders headers) {
        return this.loginOauth2ByRole(request, headers, Account.RoleType.BUYER);
    }

    //REGISTER//
    @Override
    public AuthenticationResponse signupUsernameForSeller(UsernameLoginRequest request, HttpHeaders headers) {
        return this.signupUsernameByRole(request, headers, Account.RoleType.SELLER);
    }

    @Override
    public AuthenticationResponse signupUsernameForBuyer(UsernameLoginRequest request, HttpHeaders headers) {
        return this.signupUsernameByRole(request, headers, Account.RoleType.BUYER);
    }

    @Override
    public AuthenticationResponse signupOauth2ForSeller(Oauth2LoginRequest request, HttpHeaders headers) {
        return this.loginOauth2ForSeller(request, headers);
    }

    @Override
    public AuthenticationResponse signupOauth2ForBuyer(Oauth2LoginRequest request, HttpHeaders headers) {
        return this.loginOauth2ForBuyer(request, headers);
    }

    //REFRESH//
    @Override
    public AuthenticationResponse refreshForAdmin(String refreshToken, HttpHeaders headers) {
        return this.refreshByRole(refreshToken, headers, Account.RoleType.ADMIN);
    }

    @Override
    public AuthenticationResponse refreshForSeller(String refreshToken, HttpHeaders headers) {
        return this.refreshByRole(refreshToken, headers, Account.RoleType.SELLER);
    }

    @Override
    public AuthenticationResponse refreshForBuyer(String refreshToken, HttpHeaders headers) {
        return this.refreshByRole(refreshToken, headers, Account.RoleType.BUYER);
    }

    //LOGOUT//
    @Override
    public void logoutForAdmin(HttpHeaders headers) {
        this.logout(headers);
    }

    @Override
    public void logoutForSeller(HttpHeaders headers) {
        this.logout(headers);
    }

    @Override
    public void logoutForBuyer(HttpHeaders headers) {
        this.logout(headers);
    }

    //HELPER//
    private AuthenticationResponse licenseToken(Account account, HttpHeaders headers, Account.RoleType clientType) {
        String accessToken = this.securityInfraProvider.genToken(account, TokenType.ACCESS_TOKEN);
        String refreshToken = this.securityInfraProvider.genToken(account, TokenType.REFRESH_TOKEN);
        Duration refreshDuration = this.securityInfraProvider.getValidDuration(TokenType.REFRESH_TOKEN);
        Instant refreshExpDate = this.securityInfraProvider.getExpirationDate(TokenType.REFRESH_TOKEN);

        AccountInfoResponse accountInfo = null;
        ShopInfoResponse shopInfo = null;
        if (!clientType.equals(Account.RoleType.SELLER))
            accountInfo = this.accountMapper.toAccountInfo(account);
        else
            shopInfo = this.shopMapper.toShopInfo(account.getShop());

        this.tokenDomainService.updateRefreshToken(refreshToken, refreshExpDate, account.getId());

        HttpCookie cookie = ResponseCookie.from("REFRESH_TOKEN", refreshToken)
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(refreshDuration)
                .build();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

        return AuthenticationResponse.builder()
                .authenticated(true)
                .accessToken(accessToken)
                .accountInfo(accountInfo)
                .shopInfo(shopInfo)
                .build();
    }

    private AuthenticationResponse loginUsernameByRole(UsernameLoginRequest request,
                                                       HttpHeaders headers,
                                                       Account.RoleType roleType) {
        Account account = this.accountDomainService.getByUsernameOrError(request.getUsername());

        if (!account.getRoles().contains(roleType.toString()))
            throw new AppException(ErrorCode.ACCOUNT__NOT_FOUND);

        if (!this.securityInfraProvider.matchPassword(request.getPassword(), account.getPassword()))
            throw new AppException(ErrorCode.SECURITY__WRONG_PASSWORD);

        return this.licenseToken(account, headers, roleType);
    }

    private AuthenticationResponse loginOauth2ByRole(Oauth2LoginRequest request,
                                                     HttpHeaders headers,
                                                     Account.RoleType roleType) {
        //only support google//
        Oauth2Type oauth2Type;
        try {
            oauth2Type = Oauth2Type.valueOf(request.getServer());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.SYSTEM__DEVELOPING_FEATURE);
        }

        //exchange code for token//
        ExchangeTokenResponse exchangeTokenRes = this.oauth2InfraProvider.exchangeToken(request.getCode(), oauth2Type);

        //onboard user//
        Account userInfo = this.oauth2InfraProvider.getUserInfo(exchangeTokenRes.getAccessToken(), oauth2Type);
        Account account = this.accountDomainService.onboard(userInfo, roleType);

        //license a token credentials//
        return this.licenseToken(account, headers, roleType);

    }

    private AuthenticationResponse signupUsernameByRole(UsernameLoginRequest request,
                                                        HttpHeaders headers,
                                                        Account.RoleType roleType) {
        //hash password
        request.setPassword(this.securityInfraProvider.hashPassword(request.getPassword()));
        //create account
        Account account = this.accountDomainService.signup(request, roleType);
        //licence token credentials
        return this.licenseToken(account, headers, roleType);
    }

    private AuthenticationResponse refreshByRole(String refreshToken,
                                                 HttpHeaders headers,
                                                 Account.RoleType roleType) {
        Jwt jwt = this.securityInfraProvider.decodeToken(refreshToken, TokenType.REFRESH_TOKEN);

        Account account = this.accountDomainService.getByIdOrError(jwt.getSubject());

        if (!account.getToken().getRefreshToken().equals(refreshToken))
            throw new AppException(ErrorCode.SECURITY__REFRESH_TOKEN_FAILED);

        return this.licenseToken(account, headers, roleType);
    }

    private void logout(HttpHeaders headers) {
        this.tokenDomainService.updateRefreshToken(
                null, null, this.securityInfraProvider.getAccountId());

        HttpCookie cookie = ResponseCookie.from("REFRESH_TOKEN", "")
                .httpOnly(true)
                .sameSite("None")
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();
        headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

    }
}
