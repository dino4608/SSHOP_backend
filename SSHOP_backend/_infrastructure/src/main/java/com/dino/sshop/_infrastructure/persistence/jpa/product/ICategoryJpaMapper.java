package com.dino.sshop._infrastructure.persistence.jpa.product;

import com.dino.sshop._domain.product.model.projection.CategoryProj;
import com.dino.sshop._domain.product.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ICategoryJpaMapper extends JpaRepository<Category, String>, JpaSpecificationExecutor<Category> {
    //LIST//
    Page<CategoryProj> findAllProjectedBy(Pageable pageable); //or findPagedProjectedBy(Pageable pageable) are right
    List<CategoryProj> findAllProjectedBy(Sort sort); //or findAllProjectedByOrderByPositionAsc() are right

    //FIND//
    Optional<Category> findFirstByName(String name);
    Optional<Category> findFirstByNameAndIdNot(String name, String cateId);
}
