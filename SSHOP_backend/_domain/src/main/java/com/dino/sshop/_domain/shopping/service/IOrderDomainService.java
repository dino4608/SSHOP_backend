package com.dino.sshop._domain.shopping.service;

import com.dino.sshop._domain.shopping.model.entity.Order;
import com.dino.sshop._domain.shopping.model.request.OrderCheckoutReq;
import com.dino.sshop._domain.shopping.model.request.OrderPlaceReq;
import com.dino.sshop._domain.shopping.model.response.OrderCheckoutRes;

/**
 * key features:
 * - checkout an order
 * - place an order
 */
public interface IOrderDomainService {

    //CREATE//
    OrderCheckoutRes checkout(OrderCheckoutReq orderCheckoutReq, String userId);

    //UPDATE//
    Void place(OrderPlaceReq orderPlaceReq);

    //HELPER//
    Order findOrError(String orderId);
}
