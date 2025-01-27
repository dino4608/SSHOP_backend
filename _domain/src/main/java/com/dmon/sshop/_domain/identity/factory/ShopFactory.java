package com.dmon.sshop._domain.identity.factory;

import com.dmon.sshop._domain.common.util.AppUtil;
import com.dmon.sshop._domain.identity.model.entity.Account;
import com.dmon.sshop._domain.identity.model.entity.Shop;

public class ShopFactory {
    public static Shop createShop(Shop shop, Account seller) {
        //status
        shop.setStatus(Shop.StatusType.LIVE.name());
        //shop code
        if (AppUtil.isEmpty(shop.getShopCode()))
            shop.setShopCode("shop" + System.currentTimeMillis());
        //shop name
        if (AppUtil.isEmpty(shop.getShopName()))
            shop.setStatus(Shop.StatusType.LACK_INFO.name());
        //seller
        shop.setSeller(seller);
        //response
        return shop;
    }
}
