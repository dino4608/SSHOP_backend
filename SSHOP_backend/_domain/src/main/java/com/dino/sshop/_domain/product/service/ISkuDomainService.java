package com.dino.sshop._domain.product.service;

import com.dino.sshop._domain.inventory.model.entity.Sku;

public interface ISkuDomainService {
    //SERVICES//

    /**
     * @param skuCode String
     * @desc check if the value of field is unique.
     */
    void checkSkuCodeOrError(String skuCode);

    Sku findOrError(String skuId);
}
