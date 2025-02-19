package com.dino.sshop._application.service.product.impl;

import com.dino.sshop._application.service.product.ICategoryAppService;
import com.dino.sshop._domain.common.base.PageRes;
import com.dino.sshop._domain.product.model.projection.CategoryProj;
import com.dino.sshop._domain.product.model.request.CategoryReq;
import com.dino.sshop._domain.product.model.entity.Category;
import com.dino.sshop._domain.product.service.ICategoryDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryAppServiceImpl implements ICategoryAppService {
    ICategoryDomainService categoryDomainService;

    //CREATE//
    @Override
    public Category create(CategoryReq.Create cateDto) {
        return this.categoryDomainService.create(cateDto);
    }

    //UPDATE//
    @Override
    public Category update(CategoryReq.Update cateDto, String cateId) {
        return this.categoryDomainService.update(cateDto, cateId);
    }

    //LIST//
    @Override
    public PageRes<CategoryProj> findAll(Pageable pageable) {
        return this.categoryDomainService.findAll(pageable);
    }

    @Override
    public List<CategoryProj> findTree() {
        return this.categoryDomainService.findTree();
    }

    //FIND//
    @Override
    public Category find(String cateId) {
        return this.categoryDomainService.find(cateId);
    }

    //DELETE//
    @Override
    public Void delete(String cateId) {
        return this.categoryDomainService.delete(cateId);
    }

}
