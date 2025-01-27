package com.dmon.sshop._domain.identity.service.impl;

import com.dmon.sshop._domain.identity.service.IAccessDomainService;
import org.springframework.stereotype.Service;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccessDomainServiceImpl implements IAccessDomainService {

    /**
     * - Super classes: AccessDomainService, AccountDomainService: define contract
     * applying Sub classes
     * - Sub classes: UserDomainService, SellerDomainService: implement Super
     * classes
     * - Factory class: AccountDomainService
     */

    SellerDomainServiceImpl sellerService;

    // SIGN UP//
    @Override
    public AccountRes.Signup signup(AccountReq.Create accessDto, Account.RoleType role) {
        // apply factory pattern
        // todo: build sup, sub classes to follow factory pattern
        if (role.equals(Account.RoleType.ADMIN))
            throw new AppException(ErrorCode.SYSTEM__DEVELOPING_FEATURE);
        if (role.equals(Account.RoleType.SELLER))
            return this.sellerService.create(accessDto);
        if (role.equals(Account.RoleType.BUYER))
            throw new AppException(ErrorCode.SYSTEM__DEVELOPING_FEATURE);
        throw new AppException(ErrorCode.SYSTEM__KEY_UNSUPPORTED); // todo: build a supported exception
    }
}
