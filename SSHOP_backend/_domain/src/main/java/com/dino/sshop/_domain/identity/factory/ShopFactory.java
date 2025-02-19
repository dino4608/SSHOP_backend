package com.dino.sshop._domain.identity.factory;

import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.common.util.AppUtils;
import com.dino.sshop._domain.identity.model.entity.Account;
import com.dino.sshop._domain.identity.model.entity.Shop;

public class ShopFactory {
    public static Shop createShop(Shop shop, Account seller) {
        //status
        shop.setStatus(Shop.StatusType.LIVE.name());
        //shop code
        if (AppUtils.isEmpty(shop.getShopCode()))
            shop.setShopCode("shop" + System.currentTimeMillis());
        //shop name
        if (AppUtils.isEmpty(shop.getShopName()))
            shop.setStatus(Shop.StatusType.LACK_INFO.name());
        //business type
        if (AppUtils.isPresent(shop.getBusinessType())) {
            try {
                Shop.BusinessType.valueOf(shop.getBusinessType());
            } catch (IllegalArgumentException e) {
                throw new AppException(ErrorCode.SYSTEM__KEY_UNSUPPORTED);
            }
        }
        //seller
        shop.setSeller(seller);
        //response
        return shop;
    }

    public static Shop updateShop(Shop shop) {
        //status
        shop.setStatus(Shop.StatusType.LIVE.name());
        //shop code
        if (AppUtils.isEmpty(shop.getShopCode()))
            shop.setShopCode("shop" + System.currentTimeMillis());
        //shop name
        if (AppUtils.isEmpty(shop.getShopName()))
            shop.setStatus(Shop.StatusType.LACK_INFO.name());
        //business type *change
        if (AppUtils.isEmpty(shop.getBusinessType()))
            throw new AppException(ErrorCode.ACCOUNT__LACK_INFO);
        try {
            Shop.BusinessType.valueOf(shop.getBusinessType());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.SYSTEM__KEY_UNSUPPORTED);
        }
        //seller type *change
        shop.setSellerType("LOCAL");
        //response
        return shop;
    }

    public static Shop responseShop(Shop shop) {
        shop.setContactEmail(null);
        shop.setContactPhone(null);
        return shop;
    }
}
