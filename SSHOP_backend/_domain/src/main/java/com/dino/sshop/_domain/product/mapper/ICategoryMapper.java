package com.dino.sshop._domain.product.mapper;

import com.dino.sshop._domain.product.model.request.CategoryReq;
import com.dino.sshop._domain.product.model.response.CategoryRes;
import com.dino.sshop._domain.product.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
    Category toEntity(CategoryReq.Create payload);
    Category toEntity(CategoryReq.Update payload);
    CategoryRes toRes(Category category);
}
