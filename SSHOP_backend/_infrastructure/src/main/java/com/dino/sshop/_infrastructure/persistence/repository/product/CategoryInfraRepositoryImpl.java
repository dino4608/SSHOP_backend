package com.dino.sshop._infrastructure.persistence.repository.product;

import com.dino.sshop._domain.product.model.projection.CategoryProj;
import com.dino.sshop._domain.product.model.entity.Category;
import com.dino.sshop._domain.product.repository.ICategoryDomainRepository;
import com.dino.sshop._infrastructure.persistence.jpa.product.ICategoryJpaMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryInfraRepositoryImpl implements ICategoryDomainRepository {
    ICategoryJpaMapper categoryJpaMapper;

    @Override
    public Page<CategoryProj> findAllProjectedBy(Pageable pageable) {
        return this.categoryJpaMapper.findAllProjectedBy(pageable);
    }

    @Override
    public List<CategoryProj> findAllProjectedBy(Sort sort) {
        return this.categoryJpaMapper.findAllProjectedBy(sort);
    }

    @Override
    public Optional<Category> findById(String name) {
        return this.categoryJpaMapper.findById(name);
    }

    @Override
    public Optional<Category> findFirstByName(String name) {
        return this.categoryJpaMapper.findFirstByName(name);
    }

    @Override
    public Optional<Category> findFirstByNameAndIdNot(String name, String id) {
        return this.categoryJpaMapper.findFirstByNameAndIdNot(name, id);
    }

    @Override
    public Category save(Category category) {
        return this.categoryJpaMapper.save(category);
    }

    @Override
    public void deleteById(String id) {
        this.categoryJpaMapper.deleteById(id);
    }
}
