package com.dmon.sshop._domain.product.service.impl;

import com.dmon.sshop._domain.inventory.model.entity.Sku;
import org.springframework.stereotype.Service;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.product.repository.ISkuDomainRepository;
import com.dmon.sshop._domain.product.service.ISkuDomainService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
@Slf4j
public class SkuDomainServiceImpl implements ISkuDomainService {
    ISkuDomainRepository skuDomainRepo;

    // SERVICES//
    @Override
    public void checkSkuCodeOrError(String skuCode) {
        this.skuDomainRepo.findBySkuCode(skuCode)
                .ifPresent(ignore -> {
                    throw new AppException(ErrorCode.SKU__CODE_UNIQUE);
                });

    }

    @Override
    public Sku findOrError(String skuId) {
        Sku skuPresent = this.skuDomainRepo.findById(skuId)
                .orElseThrow(() -> new AppException(ErrorCode.SKU__NOT_FOUND));

        return skuPresent;
    }
}
