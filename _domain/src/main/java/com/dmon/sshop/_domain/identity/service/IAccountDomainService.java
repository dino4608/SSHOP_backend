package com.dmon.sshop._domain.identity.service;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.request.UsernameLoginRequest;
import com.dmon.sshop._domain.identity.model.response.AccountRes;

import java.util.List;

public interface IAccountDomainService {

    //PROFILE//
    Account getProfile(String accountId);

    // CREATE//
    Account onboard(Account account, Account.RoleType roleType);

    Account signup(UsernameLoginRequest request, Account.RoleType roleType);

    AccountRes createOne(AccountReq.Create accountDto);

    Account preparePreCreate(AccountReq.Create accountDto, Account.RoleType roleType);

    //READ//
    List<Account> listAll();

    AccountRes findOne(String accountId);

    AccountRes findMyOne();

    // UPDATE//
    AccountRes updateOne(String accountId, AccountReq.Update body);

    // DELETE//
    void deleteOne(String accountId);

    //HELPER//
    Account getByIdOrError(String accountId);

    Account getByEmailOrNull(String username);

    Account getByUsernameOrError(String username);

    Account getByUsernameOrNull(String username);

    void checkUsernameIsUnique(String username);
}
