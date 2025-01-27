package com.dmon.sshop._domain.identity.service.impl;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.mapper.IAccessMapper;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import com.dmon.sshop._domain.identity.repository.ISellerDomainRepository;
import com.dmon.sshop._domain.identity.service.IAccessFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SellerDomainServiceImpl implements IAccessFactory {

    ISellerDomainRepository sellerRepo;
    IAccessMapper accessMapper;
    AccountDomainServiceImpl accountService;

    // FACTORY//
    @Override
    public AccountRes.Signup create(AccountReq.Create accountDto) {
//         Account accountCreated = this.accountService
//             .preparePreCreate(accountDto, Account.RoleType.SELLER);
//         Account sellerCreated = Account.builder()
//             .account(accountCreated)
//             .status(Seller.StatusType.REGISTERING.name())
//             .build();
//
//         Seller sellerResult = this.sellerRepo.save(sellerCreated);
//         return this.accessMapper.toRes(sellerResult);
        throw new AppException(ErrorCode.SYSTEM__UNIMPLEMENTED_FEATURE); //todo
    }
}
