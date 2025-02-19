package com.dino.sshop._domain.shopping.service.impl;

import com.dino.sshop._domain.common.exception.AppException;
import com.dino.sshop._domain.common.exception.ErrorCode;
import com.dino.sshop._domain.inventory.model.entity.Sku;
import com.dino.sshop._domain.product.repository.ISkuDomainRepository;
import com.dino.sshop._domain.shopping.factory.CartFactory;
import com.dino.sshop._domain.shopping.mapper.ICartMapper;
import com.dino.sshop._domain.shopping.model.entity.Cart;
import com.dino.sshop._domain.shopping.model.entity.CartItem;
import com.dino.sshop._domain.shopping.model.request.CartItemEditReq;
import com.dino.sshop._domain.shopping.model.request.CartItemAddReq;
import com.dino.sshop._domain.shopping.repository.ICartDomainRepository;
import com.dino.sshop._domain.shopping.repository.ICartItemDomainRepository;
import com.dino.sshop._domain.shopping.service.ICartDomainService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartDomainServiceImpl implements ICartDomainService {

    ICartDomainRepository cartDomainRepo;
    ICartItemDomainRepository cartItemDomainRepo;
    ISkuDomainRepository skuDomainRepo;
    ICartMapper cartMapper;

    //CREATE//
    @Override
    public Cart create(String userId) {
        final Cart cartCreated = CartFactory.createCart(userId);
        Cart cartResult = this.cartDomainRepo.save(cartCreated);

        return cartResult;
    }

    @Override
    public CartItem addToCart(CartItemAddReq cartItemAddReq, String userId) {
        CartItem cartItemRequested = this.cartMapper.toEntity(cartItemAddReq);

        cartItemRequested.setCart(
                this.cartDomainRepo.findById(userId)
                        .orElseThrow(() -> new AppException(ErrorCode.CART__NOT_FOUND)));

        cartItemRequested.setSku(
                this.skuDomainRepo.findById(cartItemRequested.getSku().getId())
                        .orElseThrow(() -> new AppException(ErrorCode.SKU__NOT_FOUND)));

        //if the cart item is present, edit the quantity
        Optional<CartItem> cartItemOpt = this.cartItemDomainRepo.findBySkuAndCart(
                Sku.builder().id(cartItemRequested.getSku().getId()).build(),
                Cart.builder().id(cartItemRequested.getId()).build());

        if (cartItemOpt.isPresent()) {
            CartItemEditReq cartItemEditReq = this.cartMapper.toReq(cartItemOpt.get());

            cartItemEditReq.setQuantity(cartItemEditReq.getQuantity() + cartItemAddReq.getQuantity());

            return this.editQuantity(cartItemEditReq);
        }

        //if not, add to cart
        CartItem cartItemCreated = CartFactory.addToCart(cartItemRequested);

        return this.cartItemDomainRepo.save(cartItemCreated);

    }

    //READ//
    @Override
    public Object get(String userId) {
        Cart cartPresent = this.cartDomainRepo.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.CART__NOT_FOUND));

        return cartPresent;
    }

    //UPDATE//
    @Override
    public CartItem editQuantity(CartItemEditReq cartItemEditReq) {
        CartItem cartItemRequested = this.cartMapper.toEntity(cartItemEditReq);

        this.cartItemDomainRepo.findById(cartItemRequested.getId())
                .orElseThrow(() -> new AppException(ErrorCode.CART__ITEM_NOT_FOUND));

        CartItem cartItemUpdated = CartFactory.editQuantity(cartItemRequested);

        return this.cartItemDomainRepo.save(cartItemUpdated);
    }

    //DELETE//
    @Override
    public Object removeFromCart(String cartItemIds) {
        this.cartItemDomainRepo.deleteAllById(
                new ArrayList<>(Arrays.asList(cartItemIds.split(","))));

        return null;
    }

}
