package com.dino.sshop._domain.inventory.service;

import com.dino.sshop._domain.inventory.model.entity.Inventory;

public interface IInventoryDomainService {
    //HELPER//
    Inventory findOrError(String invId);
}
