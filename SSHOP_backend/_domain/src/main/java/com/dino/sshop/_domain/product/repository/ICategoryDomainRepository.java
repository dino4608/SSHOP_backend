package com.dino.sshop._domain.product.repository;

import com.dino.sshop._domain.product.model.projection.CategoryProj;
import com.dino.sshop._domain.product.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface ICategoryDomainRepository {
    //LIST//
    Page<CategoryProj> findAllProjectedBy(Pageable pageable); //or findPagedProjectedBy(Pageable pageable) are right
    List<CategoryProj> findAllProjectedBy(Sort sort); //or findAllProjectedByOrderByPositionAsc() are right

    //FIND//
    Optional<Category> findById(String name);
    Optional<Category> findFirstByName(String name);
    Optional<Category> findFirstByNameAndIdNot(String name, String cateId);

    //PERSIST//
    Category save(Category category);
    void deleteById(String id);
}
