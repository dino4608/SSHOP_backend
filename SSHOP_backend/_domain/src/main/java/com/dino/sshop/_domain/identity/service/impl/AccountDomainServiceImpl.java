package com.dino.sshop._domain.identity.service.impl;

import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.common.util.AppUtils;
import com.dino.sshop._domain.identity.factory.AccountFactory;
import com.dino.sshop._domain.identity.mapper.IAccountMapper;
import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.identity.model.request.AccountSettleRequest;
import com.dino.sshop._domain.identity.model.request.ShopSettleRequest;
import com.dino.sshop._domain.identity.model.request.UsernameLoginRequest;
import com.dino.sshop._domain.identity.model.response.*;
import com.dino.sshop._domain.identity.repository.IAccountDomainRepository;
import com.dino.sshop._domain.identity.service.IAccountDomainService;
import com.dino.sshop._domain.identity.service.IShopDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountDomainServiceImpl implements IAccountDomainService {

    IShopDomainService shopDomainService;
    IAccountDomainRepository accountDomainRepo;
    IAccountMapper accountMapper;

    //CREATE//
    @Override
    public Account onboard(Account account, Account.RoleType roleType) {
        account = this.getByEmailOrNull(account.getEmail());
        if (AppUtils.isPresent(account)) {
            //if account is existed and role is right -> return
            if (account.getRoles().contains(roleType.name()))
                return account;
            //if account is existed and role is new -> add role
            account.getRoles().add(roleType.name());
        } else {
            //if account is not existed -> create new
            account = Account.builder().build();
            account.setRoles(new HashSet<>(Collections.singletonList(roleType.name())));
        }
        return this.accountDomainRepo.save(AccountFactory.createAccount(account));
    }

    @Override
    public Account signup(UsernameLoginRequest request, Account.RoleType roleType) {
        Account account = this.getByUsernameOrNull(request.getUsername());
        if (AppUtils.isPresent(account)) {

            if (account.getRoles().contains(Account.RoleType.ADMIN.name()))
                //if account is admin -> forbidden
                throw new AppException(ErrorCode.SECURITY__FORBIDDEN);
            else
                //if account is buyer or seller -> existed
                throw new AppException(ErrorCode.ACCOUNT__EXISTED_BUYER_SELLER);
        }
        //if account is not existed -> create new
        account = this.accountMapper.toAccount(request);
        account.setRoles(new HashSet<>(Collections.singletonList(roleType.name())));
        return this.accountDomainRepo.save(AccountFactory.createAccount(account));
    }

    //READ//
    @Override
    public AccountInfoResponse getAccountInfo(String accountId) {
        Account account = this.getByIdOrError(accountId);
        return this.accountMapper.toAccountInfo(account);
    }

    @Override
    public ShopInfoResponse getShopInfo(String accountId) {
        return this.shopDomainService.getShopInfo(accountId);
    }

    @Override
    public ContactInfoResponse getContactInfo(String accountId) {
        return this.shopDomainService.getContactInfo(accountId);
    }

    @Override
    public LoginInfoResponse getLoginInfo(String accountId) {
        Account account = this.getByIdOrError(accountId);
        return LoginInfoResponse.builder()
                .username(account.getUsername())
                .maskedEmail(AppUtils.maskMiddle(account.getEmail(), 1))
                .maskedPhone(AppUtils.maskStart(account.getPhone(), 4))
                .maskedPassword("**********")
                .build();
    }

    @Override
    public CitizenInfoResponse getCitizenInfo(String accountId) {
        return CitizenInfoResponse.builder()
                .maskedCard(AppUtils.maskMiddle("084444444460", 2))
                .maskedName(AppUtils.maskMiddle("Dino Nguyen Tran", 2))
                .build();
    }

    @Override
    public Account getOne(String accountId) {
        Account account = this.accountDomainRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT__NOT_FOUND));
        return AccountFactory.responseAccount(account);
    }

    @Override
    public List<Account> listAll() {
        List<Account> accounts;
        accounts = this.accountDomainRepo.findAll();
        return accounts;
    }

    //UPDATE//
    @Override
    public Account settleAccountInfo(AccountSettleRequest request, String accountId) {
        Account account = this.getByIdOrError(accountId);
        account = this.accountMapper.toAccount(request, account);
        account = this.accountDomainRepo.save(AccountFactory.updateAccount(account));
        return AccountFactory.responseAccount(account);
    }

    @Override
    public Shop settleShopInfo(ShopSettleRequest request, String accountId) {
        return this.shopDomainService.settleShopInfo(request, accountId);
    }


    //DELETE//

    //HELPER//
    @Override
    public Account getByIdOrError(String accountId) {
        return this.accountDomainRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT__NOT_FOUND));
    }

    @Override
    public Account getByEmailOrNull(String email) {
        return this.accountDomainRepo.findByEmail(email).orElse(null);
    }

    @Override
    public Account getByUsernameOrError(String username) {
        return this.accountDomainRepo.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT__NOT_FOUND));
    }

    @Override
    public Account getByUsernameOrNull(String username) {
        return this.accountDomainRepo.findByUsername(username).orElse(null);
    }

    @Override
    public void checkUsernameIsUnique(String username) {
        if (this.accountDomainRepo.findByUsername(username).isPresent())
            throw new AppException(ErrorCode.ACCOUNT__EXISTED);
    }
}
