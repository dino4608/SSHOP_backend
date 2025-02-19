package com.dino.sshop._application.service.shopping;

import com.dino.sshop._domain.shopping.model.request.OrderCheckoutReq;
import com.dino.sshop._domain.shopping.model.request.OrderPlaceReq;
import com.dino.sshop._domain.shopping.model.response.OrderCheckoutRes;

public interface IOrderAppService {
    //CREATE//
    OrderCheckoutRes checkout(OrderCheckoutReq orderCheckoutReq);

    //UPDATE//
    Void place(OrderPlaceReq orderPlaceReq);
}
