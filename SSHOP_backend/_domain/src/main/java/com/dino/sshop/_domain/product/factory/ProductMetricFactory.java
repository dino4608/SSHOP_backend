package com.dino.sshop._domain.product.factory;

import com.dino.sshop._domain.product.model.entity.Product;
import com.dino.sshop._domain.product.model.entity.ProductMetric;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductMetricFactory {
    //CREATE//
    public ProductMetric create(Product product) {
        ProductMetric productMetric = ProductMetric.builder()
                .product(product)
                .sales(0)
                .views(0)
                .carts(0)
                .stocks(product.getSkus().stream().parallel()
                        .map(sku -> sku.getInventory().getStocks())
                        .reduce(0, Integer::sum)
                )
                .build();

        return productMetric;
    }
}
