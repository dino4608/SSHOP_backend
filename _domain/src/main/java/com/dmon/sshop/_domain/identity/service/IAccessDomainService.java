package com.dmon.sshop._domain.identity.service;

import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.model.entity.Account;

public interface IAccessDomainService {
    // CREATE//
    AccountRes.Signup signup(AccountReq.Create accessDto, Account.RoleType role);
}
