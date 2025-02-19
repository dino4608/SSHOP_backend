package com.dino.sshop._domain.identity.mapper;

import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._domain.identity.model.request.AccountSettleRequest;
import com.dino.sshop._domain.identity.model.request.UsernameLoginRequest;
import com.dino.sshop._domain.identity.model.response.AccountInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IAccountMapper {

    Account toAccount(UsernameLoginRequest request);

    Account toAccount(AccountSettleRequest request, @MappingTarget Account account);

    AccountInfoResponse toAccountInfo(Account account);
}
