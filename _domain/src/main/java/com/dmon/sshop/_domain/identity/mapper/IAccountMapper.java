package com.dmon.sshop._domain.identity.mapper;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.model.request.AccountReq;
import com.dmon.sshop._domain.identity.model.request.UsernameLoginRequest;
import com.dmon.sshop._domain.identity.model.response.AccountInfoResponse;
import com.dmon.sshop._domain.identity.model.response.AccountRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountMapper {
    Account toEntity(AccountReq.Create dto);

    Account toEntity(AccountReq.Update dto);

    Account toEntity(UsernameLoginRequest request);

    AccountRes toRes(Account account);

    AccountInfoResponse toAccountInfo(Account account);
}
