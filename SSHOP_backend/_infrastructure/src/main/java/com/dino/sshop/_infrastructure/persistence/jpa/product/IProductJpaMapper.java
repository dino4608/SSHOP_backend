package com.dino.sshop._infrastructure.persistence.jpa.product;

import com.dino.sshop._domain.product.model.projection.ProductProj;
import com.dino.sshop._domain.product.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProductJpaMapper extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    //LIST//
    Page<ProductProj> findAllProjectedBy(Pageable pageable);
}
