package com.dmon.sshop._domain.identity.service;

import com.dmon.sshop._domain.identity.model.entity.Shop;

public interface IShopDomainService {

    Shop findOrError(String sellerId);

}
