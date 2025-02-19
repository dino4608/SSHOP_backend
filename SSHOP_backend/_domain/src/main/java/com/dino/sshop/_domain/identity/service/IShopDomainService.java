package com.dino.sshop._domain.identity.service;

import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.identity.model.request.ShopSettleRequest;
import com.dino.sshop._domain.identity.model.response.ContactInfoResponse;
import com.dino.sshop._domain.identity.model.response.ShopInfoResponse;

public interface IShopDomainService {
    //READ//
    ShopInfoResponse getShopInfo(String shopId);

    ContactInfoResponse getContactInfo(String shopId);

    //UPDATE//
    Shop settleShopInfo(ShopSettleRequest request, String shopId);

    //HELPER//
    Shop getByIdOrError(String shopId);

}
