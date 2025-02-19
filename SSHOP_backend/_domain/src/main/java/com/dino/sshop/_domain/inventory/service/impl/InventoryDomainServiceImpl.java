package com.dino.sshop._domain.inventory.service.impl;

import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.inventory.model.entity.Inventory;
import com.dino.sshop._domain.inventory.repository.IInventoryDomainRepository;
import com.dino.sshop._domain.inventory.service.IInventoryDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InventoryDomainServiceImpl implements IInventoryDomainService {

    IInventoryDomainRepository invDomainRepo;

    @Override
    public Inventory findOrError(String invId) {
        Inventory invPresent = this.invDomainRepo.findById(invId)
                .orElseThrow(() -> new AppException(ErrorCode.INVENTORY__SKU_NOT_FOUND));

        return invPresent;
    }
}
