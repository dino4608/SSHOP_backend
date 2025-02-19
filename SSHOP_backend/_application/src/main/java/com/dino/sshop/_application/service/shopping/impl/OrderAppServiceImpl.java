package com.dino.sshop._application.service.shopping.impl;

import com.dino.sshop._application.service.shopping.IOrderAppService;
import com.dino.sshop._domain.shopping.model.request.OrderCheckoutReq;
import com.dino.sshop._domain.shopping.model.request.OrderPlaceReq;
import com.dino.sshop._domain.shopping.model.response.OrderCheckoutRes;
import com.dino.sshop._domain.shopping.service.IOrderDomainService;
import com.dino.sshop._infrastructure.security.provider.ISecurityInfraProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderAppServiceImpl implements IOrderAppService {

    IOrderDomainService orderDomainService;
    ISecurityInfraProvider securityInfraHelper;

    @Override
    public OrderCheckoutRes checkout(OrderCheckoutReq orderCheckoutReq) {
        return this.orderDomainService.checkout(orderCheckoutReq, this.securityInfraHelper.getAccountId());
    }

    @Override
    public Void place(OrderPlaceReq orderPlaceReq) {
        return null;
    }
}
