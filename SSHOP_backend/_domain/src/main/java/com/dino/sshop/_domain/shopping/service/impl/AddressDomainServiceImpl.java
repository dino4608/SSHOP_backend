package com.dino.sshop._domain.shopping.service.impl;

import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.shopping.model.entity.Address;
import com.dino.sshop._domain.shopping.repository.IAddressDomainRepository;
import com.dino.sshop._domain.shopping.service.IAddressDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AddressDomainServiceImpl implements IAddressDomainService {

    IAddressDomainRepository addressDomainRepo;

    //HELPER//
    @Override
    public Address findOrError(String addressId) {
        Address addressPresent = this.addressDomainRepo.findById(addressId)
                .orElseThrow(() -> new AppException(ErrorCode.ADDRESS__NOT_FOUND));

        return addressPresent;
    }
}
