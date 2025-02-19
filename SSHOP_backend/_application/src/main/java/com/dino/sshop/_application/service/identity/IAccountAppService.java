package com.dino.sshop._application.service.identity;

import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.identity.model.request.AccountSettleRequest;
import com.dino.sshop._domain.identity.model.request.ShopSettleRequest;
import com.dino.sshop._domain.identity.model.response.*;

import java.util.List;

public interface IAccountAppService {

    AccountInfoResponse getAccountInfo();

    ShopInfoResponse getShopInfo();

    ContactInfoResponse getContactInfo();

    LoginInfoResponse getLoginInfo();

    CitizenInfoResponse getCitizenInfo();

    Account getOne(String accountId);

    List<Account> listAll();

    Account settleAccountInfo(AccountSettleRequest request);

    Shop settleShopInfo(ShopSettleRequest request);
}
