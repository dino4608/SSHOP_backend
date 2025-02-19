package com.dino.sshop._domain.identity.service;

import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.identity.model.request.AccountSettleRequest;
import com.dino.sshop._domain.identity.model.request.ShopSettleRequest;
import com.dino.sshop._domain.identity.model.request.UsernameLoginRequest;
import com.dino.sshop._domain.identity.model.response.*;

import java.util.List;

public interface IAccountDomainService {
    // CREATE//
    Account onboard(Account account, Account.RoleType roleType);

    Account signup(UsernameLoginRequest request, Account.RoleType roleType);

    //READ//
    AccountInfoResponse getAccountInfo(String accountId);

    ShopInfoResponse getShopInfo(String accountId);

    ContactInfoResponse getContactInfo(String accountId);

    LoginInfoResponse getLoginInfo(String accountId);

    CitizenInfoResponse getCitizenInfo(String accountId);

    Account getOne(String accountId);

    List<Account> listAll();

    // UPDATE//
    Account settleAccountInfo(AccountSettleRequest request, String accountId);

    Shop settleShopInfo(ShopSettleRequest request, String accountId);

    // DELETE//

    //HELPER//
    Account getByIdOrError(String accountId);

    Account getByEmailOrNull(String username);

    Account getByUsernameOrError(String username);

    Account getByUsernameOrNull(String username);

    void checkUsernameIsUnique(String username);
}
