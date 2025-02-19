package com.dino.sshop._application.service.identity.impl;

import com.dino.sshop._application.service.identity.IAccountAppService;
import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.identity.model.request.AccountSettleRequest;
import com.dino.sshop._domain.identity.model.request.ShopSettleRequest;
import com.dino.sshop._domain.identity.model.response.*;
import com.dino.sshop._domain.identity.service.IAccountDomainService;
import com.dino.sshop._infrastructure.security.provider.ISecurityInfraProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountAppServiceImpl implements IAccountAppService {

    IAccountDomainService accountDomainService;
    ISecurityInfraProvider securityInfraProvider;

    @Override
    public AccountInfoResponse getAccountInfo() {
        return this.accountDomainService.getAccountInfo(this.securityInfraProvider.getAccountId());
    }

    @Override
    public ShopInfoResponse getShopInfo() {
        return this.accountDomainService.getShopInfo(this.securityInfraProvider.getAccountId());
    }

    @Override
    public ContactInfoResponse getContactInfo() {
        return this.accountDomainService.getContactInfo(this.securityInfraProvider.getAccountId());
    }

    @Override
    public LoginInfoResponse getLoginInfo() {
        return this.accountDomainService.getLoginInfo(this.securityInfraProvider.getAccountId());
    }

    @Override
    public CitizenInfoResponse getCitizenInfo() {
        return this.accountDomainService.getCitizenInfo(this.securityInfraProvider.getAccountId());
    }

    @Override
    public Account getOne(String accountId) {
        return this.accountDomainService.getOne(accountId);
    }

    @Override
    public List<Account> listAll() {
        return this.accountDomainService.listAll();
    }

    @Override
    public Account settleAccountInfo(AccountSettleRequest request) {
        return this.accountDomainService.settleAccountInfo(request, this.securityInfraProvider.getAccountId());
    }

    @Override
    public Shop settleShopInfo(ShopSettleRequest request) {
        return this.accountDomainService.settleShopInfo(request, this.securityInfraProvider.getAccountId());
    }
}
