package com.dino.sshop._domain.inventory.factory;

import com.dino.sshop._domain.common.util.AppUtils;
import com.dino.sshop._domain.inventory.model.entity.Sku;
import com.dino.sshop._domain.product.model.entity.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SkuFactory {
    InventoryFactory invFactory;

    // CREATE//
    public List<Sku> create(Product product) {
        List<Sku> skus = product.getSkus();
        skus.stream().parallel()
                .forEach(sku -> {
                    sku.setProduct(product);

                    sku.setStatus(Sku.StatusType.LIVE.name());

                    if (AppUtils.isEmpty(sku.getSkuCode()))
                        sku.setSkuCode(this.genSkuCode());

                    sku.setCarts(0);

                    sku.setInventory(this.invFactory.create(sku)); // cascade
                });

        return skus;
    }

    // FIELDS//
    public String genSkuCode() {
        String skuCode = AppUtils.genUUID();

        return skuCode;
    }
}
