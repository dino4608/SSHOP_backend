package com.dino.sshop._application.service.identity.impl;

import com.dino.sshop._application.external.security.IAccessSecurityService;
import com.dino.sshop._application.service.identity.IAccessAppService;
import com.dino.sshop._domain.identity.model.request.Oauth2LoginRequest;
import com.dino.sshop._domain.identity.model.request.UsernameLoginRequest;
import com.dino.sshop._domain.identity.model.response.AuthenticationResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccessAppServiceImpl implements IAccessAppService {

    IAccessSecurityService accessSecurityService;

    @Override
    public AuthenticationResponse loginUsernameForAdmin(UsernameLoginRequest request, HttpHeaders headers) {
        return this.accessSecurityService.loginUsernameForAdmin(request, headers);
    }

    @Override
    public AuthenticationResponse loginUsernameForSeller(UsernameLoginRequest request, HttpHeaders headers) {
        return this.accessSecurityService.loginUsernameForSeller(request, headers);
    }

    @Override
    public AuthenticationResponse loginUsernameForBuyer(UsernameLoginRequest request, HttpHeaders headers) {
        return this.accessSecurityService.loginUsernameForBuyer(request, headers);
    }

    @Override
    public AuthenticationResponse loginOauth2ForSeller(Oauth2LoginRequest request, HttpHeaders headers) {
        return this.accessSecurityService.loginOauth2ForSeller(request, headers);
    }

    @Override
    public AuthenticationResponse loginOauth2ForBuyer(Oauth2LoginRequest request, HttpHeaders headers) {
        return this.accessSecurityService.loginOauth2ForBuyer(request, headers);
    }

    @Override
    public AuthenticationResponse signupUsernameForSeller(UsernameLoginRequest request, HttpHeaders headers) {
        return this.accessSecurityService.signupUsernameForSeller(request, headers);
    }

    @Override
    public AuthenticationResponse signupUsernameForBuyer(UsernameLoginRequest request, HttpHeaders headers) {
        return this.accessSecurityService.signupUsernameForBuyer(request, headers);
    }

    @Override
    public AuthenticationResponse signupOauth2ForSeller(Oauth2LoginRequest request, HttpHeaders headers) {
        return this.accessSecurityService.signupOauth2ForSeller(request, headers);
    }

    @Override
    public AuthenticationResponse signupOauth2ForBuyer(Oauth2LoginRequest request, HttpHeaders headers) {
        return this.accessSecurityService.signupOauth2ForBuyer(request, headers);
    }

    @Override
    public AuthenticationResponse refreshForAdmin(String refreshToken, HttpHeaders headers) {
        return this.accessSecurityService.refreshForAdmin(refreshToken, headers);
    }

    @Override
    public AuthenticationResponse refreshForSeller(String refreshToken, HttpHeaders headers) {
        return this.accessSecurityService.refreshForSeller(refreshToken, headers);
    }

    @Override
    public AuthenticationResponse refreshForBuyer(String refreshToken, HttpHeaders headers) {
        return this.accessSecurityService.refreshForBuyer(refreshToken, headers);
    }

    @Override
    public void logoutForAdmin(HttpHeaders headers) {
        this.accessSecurityService.logoutForAdmin(headers);
    }

    @Override
    public void logoutForSeller(HttpHeaders headers) {
        this.accessSecurityService.logoutForSeller(headers);
    }

    @Override
    public void logoutForBuyer(HttpHeaders headers) {
        this.accessSecurityService.logoutForBuyer(headers);
    }

}
