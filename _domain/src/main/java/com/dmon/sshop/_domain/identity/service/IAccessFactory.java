package com.dmon.sshop._domain.identity.service;

import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;

public interface IAccessFactory {
    // CREATE//
    AccountRes.Signup create(AccountReq.Create accessDto);
}
