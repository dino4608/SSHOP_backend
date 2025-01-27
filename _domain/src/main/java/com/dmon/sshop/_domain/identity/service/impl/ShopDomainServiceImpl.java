package com.dmon.sshop._domain.identity.service.impl;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.entity.Shop;
import com.dmon.sshop._domain.identity.service.IShopDomainService;
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

    //todo: repo

    @Override
    public Shop findOrError(String sellerId) {
        throw new AppException(ErrorCode.SYSTEM__UNIMPLEMENTED_FEATURE);
    }

}
