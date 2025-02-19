package com.dino.sshop._domain.product.mapper;

import com.dino.sshop._domain.product.model.request.ProductReq;
import com.dino.sshop._domain.product.model.response.ProductRes;
import com.dino.sshop._domain.product.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    Product toEntity(ProductReq.Create productDto);
    ProductRes toRes(Product entity);
}
