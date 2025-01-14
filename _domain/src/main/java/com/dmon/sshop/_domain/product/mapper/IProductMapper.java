package com.dmon.sshop._domain.product.mapper;

import com.dmon.sshop._domain.product.model.request.ProductReq;
import com.dmon.sshop._domain.product.model.response.ProductRes;
import com.dmon.sshop._domain.product.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    Product toEntity(ProductReq.Create productDto);
    ProductRes toRes(Product entity);
}
