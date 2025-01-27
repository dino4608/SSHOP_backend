package com.dmon.sshop._domain.identity.mapper;

import com.dmon.sshop._domain.identity.model.response.SellerRes;
import com.dmon.sshop._domain.identity.model.entity.Shop;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISellerMapper {
    SellerRes toRes(Shop entity);
}
