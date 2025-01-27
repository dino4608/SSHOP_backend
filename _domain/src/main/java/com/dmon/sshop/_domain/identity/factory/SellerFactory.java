package com.dmon.sshop._domain.identity.factory;

import com.dmon.sshop._domain.common.exception.AppException;
import com.dmon.sshop._domain.common.exception.ErrorCode;
import com.dmon.sshop._domain.identity.model.entity.Shop;
import com.dmon.sshop._domain.identity.repository.ISellerDomainRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SellerFactory {
    ISellerDomainRepository sellerDomainRepo;

    public Shop findById(String id) {
        final Shop seller = this.sellerDomainRepo.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SELLER__NOT_FOUND));

        return seller;
    }
}
