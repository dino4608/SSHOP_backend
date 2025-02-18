package com.dmon.sshop._application.service.product.impl;

import com.dmon.sshop._application.service.product.IProductAppService;
import com.dmon.sshop._domain.common.base.PageRes;
import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.product.model.entity.Product;
import com.dmon.sshop._domain.product.model.projection.ProductProj;
import com.dmon.sshop._domain.product.model.request.ProductReq;
import com.dmon.sshop._domain.product.service.IProductDomainService;
import com.dmon.sshop._infrastructure.security.provider.ISecurityInfraProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductAppServiceImpl implements IProductAppService {

    IProductDomainService productDomainService;
    ISecurityInfraProvider securityInfraProvider;

    //CREATE//
    @Override
    public Product create(ProductReq.Create productDto) {
        return this.productDomainService.create(productDto, this.securityInfraProvider.getAccountId());
    }

    //LIST//
    public PageRes<ProductProj> findAll(Pageable pageable) {
        return this.productDomainService.findAll(pageable);
    }

    @Override
    public Object find(String productId) {
        throw new AppException(ErrorCode.SYSTEM__DEVELOPING_FEATURE);
    }
}
