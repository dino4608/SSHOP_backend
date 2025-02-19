package com.dino.sshop._infrastructure.persistence.repository.product;

import com.dino.sshop._domain.product.model.projection.ProductProj;
import com.dino.sshop._domain.product.model.entity.Product;
import com.dino.sshop._domain.product.repository.IProductDomainRepository;
import com.dino.sshop._infrastructure.persistence.jpa.product.IProductJpaMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductInfraRepositoryImpl implements IProductDomainRepository {
    IProductJpaMapper productJpaMapper;

    @Override
    public Page<ProductProj> findAllProjectedBy(Pageable pageable) {
        return this.productJpaMapper.findAllProjectedBy(pageable);
    }

    @Override
    public Product save(Product product) {
        return this.productJpaMapper.save(product);
    }
}
