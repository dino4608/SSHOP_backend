package com.dino.sshop._domain.product.service.impl;

import com.dino.sshop._domain.common.base.PageRes;
import com.dino.sshop._domain.common.util.AppUtils;
import com.dino.sshop._domain.identity.model.entity.Shop;
import com.dino.sshop._domain.product.factory.ProductFactory;
import com.dino.sshop._domain.product.mapper.IProductMapper;
import com.dino.sshop._domain.product.model.entity.Product;
import com.dino.sshop._domain.product.model.projection.ProductProj;
import com.dino.sshop._domain.product.model.request.ProductReq;
import com.dino.sshop._domain.product.repository.IProductDomainRepository;
import com.dino.sshop._domain.product.service.ICategoryDomainService;
import com.dino.sshop._domain.product.service.IProductDomainService;
import com.dino.sshop._domain.product.service.ISkuDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductDomainServiceImpl implements IProductDomainService {
    IProductDomainRepository productDomainRepo;

    IProductMapper productMapper;

    ProductFactory productAggFactory;

    ICategoryDomainService cateDomainService;

    ISkuDomainService skuDomainService;

    //CREATE//
    @Override
    public Product create(ProductReq.Create productDto, String sellerId) {
        Product productRequested = this.productMapper.toEntity(productDto);

        productRequested.setShop(Shop.builder().id(sellerId).build());

        productRequested.setCategory(this.cateDomainService.findOrError(productRequested.getCategory().getId()));

        productRequested.getSkus().stream().parallel()
                .forEach(sku -> {
                    if (AppUtils.isPresent(sku.getSkuCode()))
                        this.skuDomainService.checkSkuCodeOrError(sku.getSkuCode());
                });

        final Product productCreated = this.productAggFactory.create(productRequested);

        Product productResult = this.productDomainRepo.save(productCreated);
        return productResult;
    }

    //LIST//
    public PageRes<ProductProj> findAll(Pageable pageable) {
        Page<ProductProj> productPage = this.productDomainRepo.findAllProjectedBy(pageable);

        return AppUtils.toPageRes(productPage);
    }
}
