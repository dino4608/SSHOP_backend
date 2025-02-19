package com.dino.sshop._domain.product.service;

import com.dino.sshop._domain.common.base.PageRes;
import com.dino.sshop._domain.product.model.projection.ProductProj;
import com.dino.sshop._domain.product.model.request.ProductReq;
import com.dino.sshop._domain.product.model.entity.Product;
import org.springframework.data.domain.Pageable;

public interface IProductDomainService {
    //CREATE//
    Product create(ProductReq.Create productDto, String sellerId);

    //LIST//
    PageRes<ProductProj> findAll(Pageable pageable);

}
