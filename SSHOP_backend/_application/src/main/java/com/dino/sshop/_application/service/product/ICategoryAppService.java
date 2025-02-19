package com.dino.sshop._application.service.product;

import com.dino.sshop._domain.common.base.PageRes;
import com.dino.sshop._domain.product.model.projection.CategoryProj;
import com.dino.sshop._domain.product.model.request.CategoryReq;
import com.dino.sshop._domain.product.model.entity.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryAppService {
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
}
