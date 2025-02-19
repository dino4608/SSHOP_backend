package com.dino.sshop._domain.product.service.impl;

import com.dino.sshop._domain.common.base.PageRes;
import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.common.util.AppUtils;
import com.dino.sshop._domain.product.mapper.ICategoryMapper;
import com.dino.sshop._domain.product.model.entity.Category;
import com.dino.sshop._domain.product.model.projection.CategoryProj;
import com.dino.sshop._domain.product.model.request.CategoryReq;
import com.dino.sshop._domain.product.repository.ICategoryDomainRepository;
import com.dino.sshop._domain.product.service.ICategoryDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryDomainServiceImpl implements ICategoryDomainService {
    ICategoryDomainRepository cateDomainRepo;

    ICategoryMapper cateMapper;

    //CREATE//
    @Override
    public Category create(CategoryReq.Create payload) {
        if (this.cateDomainRepo.findFirstByName(payload.getName()).isPresent())
            throw new AppException(ErrorCode.CATEGORY__NAME_UNIQUE);

        Category cateResult = this.cateDomainRepo.save(this.cateMapper.toEntity(payload));
        return cateResult;
    }

    //UPDATE//
    @Override
    public Category update(CategoryReq.Update cateDto, String cateId) {
        if (this.cateDomainRepo.findFirstByNameAndIdNot(cateDto.getName(), cateId).isPresent())
            throw new AppException(ErrorCode.CATEGORY__NAME_UNIQUE);

        Category cateRequested = this.cateMapper.toEntity(cateDto);

        Category cateUpdated = this.findOrError(cateId);

        AppUtils.updateNonNull(cateUpdated, cateRequested);

        Category cateResult = this.cateDomainRepo.save(cateUpdated);
        return cateResult;
    }

    //LIST//
    @Override
    public PageRes<CategoryProj> findAll(Pageable pageable) {
        Page<CategoryProj> catePage = this.cateDomainRepo.findAllProjectedBy(pageable);
        return AppUtils.toPageRes(catePage);
    }

    @Override
    public List<CategoryProj> findTree() {
        Sort sort = Sort.by(Sort.Direction.ASC, "position");
        List<CategoryProj> cateList = this.cateDomainRepo.findAllProjectedBy(sort);
        return cateList;
    }

    //FIND//
    @Override
    public Category find(String cateId) {
        Category catePresent = this.findOrError(cateId);
        return catePresent;
    }

    //DELETE//
    @Override
    public Void delete(String cateId) {
        this.cateDomainRepo.deleteById(cateId);
        return null;
    }

    //SERVICE//
    @Override
    public Category findOrError(String cateId) {
        Category cate = this.cateDomainRepo.findById(cateId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY__NOT_FOUND));
        return cate;
    }

}
