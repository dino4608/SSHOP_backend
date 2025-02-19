package com.dino.sshop._domain.identity.mapper;

import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.identity.model.request.ShopSettleRequest;
import com.dino.sshop._domain.identity.model.response.ShopInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface IShopMapper {

    Shop toShop(ShopSettleRequest request, @MappingTarget Shop shop);

    ShopInfoResponse toShopInfo(Shop shop);
}
