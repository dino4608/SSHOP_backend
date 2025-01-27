package com.dmon.sshop._domain.identity.mapper;

import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.model.request.UserReq;
import com.dmon.sshop._domain.identity.model.response.UserRes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    Account toEntity(UserReq.Create dto);
    UserRes toRes(Account entity);
}
