package com.dino.sshop._application.service.shopping.impl;

import com.dino.sshop._application.service.shopping.ICartAppService;
import com.dino.sshop._domain.shopping.model.entity.CartItem;
import com.dino.sshop._domain.shopping.model.request.CartItemAddReq;
import com.dino.sshop._domain.shopping.model.request.CartItemEditReq;
import com.dino.sshop._domain.shopping.service.ICartDomainService;
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
public class CartAppServiceImpl implements ICartAppService {

    ICartDomainService cartDomainService;
    ISecurityInfraProvider securityInfraHelper;

    //CREATE//
    @Override
    public CartItem addToCart(CartItemAddReq cartItemAddReq) {
        return this.cartDomainService.addToCart(cartItemAddReq, this.securityInfraHelper.getAccountId());
    }

    //READ//
    @Override
    public Object get() {
        return this.cartDomainService.get(this.securityInfraHelper.getAccountId());
    }

    //UPDATE//
    @Override
    public CartItem editQuantity(CartItemEditReq cartItemEditReq) {
        return this.cartDomainService.editQuantity(cartItemEditReq);
    }

    //DELETE//
    @Override
    public Object removeFromCart(String cartItemIds) {
        return this.cartDomainService.removeFromCart(cartItemIds);
    }
}
