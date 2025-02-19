package com.dino.sshop._domain.product.service;

import com.dino.sshop._domain.common.base.PageRes;
import com.dino.sshop._domain.product.model.entity.Category;
import com.dino.sshop._domain.product.model.projection.CategoryProj;
import com.dino.sshop._domain.product.model.request.CategoryReq;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryDomainService {
    //CREATE//
    Category create(CategoryReq.Create cateDto);

    //UPDATE//
    Category update(CategoryReq.Update cateDto, String cateId);

    //LIST//
    PageRes<CategoryProj> findAll(Pageable pageable);
    List<CategoryProj> findTree();

    //FIND//
    Category find(String cateId);

    //DELETE//
    Void delete(String cateId);

    //SERVICE//
    Category findOrError(String cateId);
}
