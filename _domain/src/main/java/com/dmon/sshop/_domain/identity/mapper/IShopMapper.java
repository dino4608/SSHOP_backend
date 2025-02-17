package com.dmon.sshop._domain.identity.mapper;

import com.dmon.sshop._domain.identity.model.entity.Shop;
import com.dmon.sshop._domain.identity.model.request.ShopSettleRequest;
import com.dmon.sshop._domain.identity.model.response.ShopInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IShopMapper {

    Shop toShop(ShopSettleRequest request, @MappingTarget Shop shop);

    ShopInfoResponse toShopInfo(Shop shop);
}
