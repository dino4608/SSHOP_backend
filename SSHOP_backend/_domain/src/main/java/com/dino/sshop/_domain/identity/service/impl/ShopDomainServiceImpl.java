package com.dino.sshop._domain.identity.service.impl;

import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.common.util.AppUtils;
import com.dino.sshop._domain.identity.factory.ShopFactory;
import com.dino.sshop._domain.identity.mapper.IShopMapper;
import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.identity.model.request.ShopSettleRequest;
import com.dino.sshop._domain.identity.model.response.ContactInfoResponse;
import com.dino.sshop._domain.identity.model.response.ShopInfoResponse;
import com.dino.sshop._domain.identity.repository.IShopDomainRepository;
import com.dino.sshop._domain.identity.service.IShopDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ShopDomainServiceImpl implements IShopDomainService {

    IShopDomainRepository shopDomainRepo;
    IShopMapper shopMapper;

    //READ//
    @Override
    public ShopInfoResponse getShopInfo(String shopId) {
        Shop shop = this.getByIdOrError(shopId);
        return shopMapper.toShopInfo(shop);
    }

    @Override
    public ContactInfoResponse getContactInfo(String shopId) {
        Shop shop = this.getByIdOrError(shopId);
        return ContactInfoResponse.builder()
                .maskedEmail(AppUtils.maskMiddle(shop.getContactEmail(), 1))
                .maskedPhone(AppUtils.maskStart(shop.getContactPhone(), 4))
                .build();
    }

    //UPDATE//
    @Override
    public Shop settleShopInfo(ShopSettleRequest request, String shopId) {
        Shop shop = this.getByIdOrError(shopId);
        shop = this.shopMapper.toShop(request, shop);
        shop = this.shopDomainRepo.save(ShopFactory.updateShop(shop));
        return ShopFactory.responseShop(shop);
    }

    //HELPER
    @Override
    public Shop getByIdOrError(String shopId) {
        return this.shopDomainRepo.findById(shopId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOP__NOT_FOUND));
    }

}
